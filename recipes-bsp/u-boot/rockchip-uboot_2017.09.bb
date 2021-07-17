require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "bc-native dtc-native"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

SRC_URI = "git://${WORKSPACE}/u-boot;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"

SRC_URI += " \
		file://0000-scripts-makefile-lib-overridden.patch \
		file://0001-u-boot-initial-env.patch \
		"
# Force using python2 for BSP u-boot
DEPENDS += "python-native"
EXTRA_OEMAKE += "PYTHON=nativepython"

# Needed for packing BSP u-boot
DEPENDS += "coreutils-native python-pyelftools-native"

EXTRA_OEMAKE += "PYTHON=nativepython"

# Needed for packing BSP u-boot
DEPENDS += "coreutils-native python-pyelftools-native"

python do_unpack_append() {
    if not d.getVar('S').endswith('/git'):
        # Force fetch to re-run for local source
        bb.build.write_taint('do_fetch', d)
}

do_configure_prepend() {
	# Make sure we use nativepython
	for s in `grep -rIl python ${S}`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env nativepython|' $s
	done

	# Copy prebuilt images
	if [ -e "${S}/${UBOOT_BINARY}" ]; then
		bbnote "${PN}: Found prebuilt images."
		mv ${S}/*.bin ${S}/*.img ${B}/
	fi

	[ -e "${S}/.config" ] && make -C ${S} mrproper

	if [ ! -d "${S}/../rkbin" ]; then
		cp -r "${EXTRA}/rkbin" "${S}/.."
	fi
}

RK_IDBLOCK_IMG = "idblock.img"
RK_LOADER_BIN = "loader.bin"
RK_TRUST_IMG = "trust.img"
UBOOT_IDBLOADER = "idbloader.img"
UBOOT_ITB = "u-boot.itb"
UBOOT_INITIAL_ENV = "u-boot-initial-env"

UBOOT_IMAGES = "${RK_LOADER_BIN} ${UBOOT_IDBLOADER} ${UBOOT_ITB} ${UBOOT_INITIAL_ENV}"

do_compile_append() {
	cd ${B}

	# Prepare needed files
	for d in make.sh scripts configs arch/arm/mach-rockchip; do
		cp -rT ${S}/${d} ${d}
	done

	# Remove unneeded stages from make.sh
	sed -i -e "/^select_tool/d" -e "/^clean/d" -e "/^\t*make/d" \
		make.sh

	# Pack rockchip loader images
	./make.sh ${UBOOT_MACHINE%_defconfig}

	# This is helpful when flash in blank device
	ln -sf *_loader*.bin "${RK_LOADER_BIN}"
	
	# Generate idblock image
	bbnote "${PN}: Generating ${RK_IDBLOCK_IMG} from ${RK_LOADER_BIN}"
	./tools/boot_merger --unpack "${RK_LOADER_BIN}"

	if [ -f FlashHead ];then
		cat FlashHead FlashData > "${RK_IDBLOCK_IMG}"
	else
		./tools/mkimage -n "${SOC_FAMILY}" -T rksd -d FlashData \
			"${RK_IDBLOCK_IMG}"
	fi

	cat FlashBoot >> "${RK_IDBLOCK_IMG}"

	bbnote "${PN}: Generating ${UBOOT_IDBLOADER}"
	# ./tools/mkimage -n rk3399 -T rksd -d ./tpl/u-boot-tpl.bin ./idbloader.img
	./tools/mkimage -n rk3399 -T rksd -d ../rkbin/bin/rk33/rk3399_ddr_933MHz_v1.25.bin idbloader.img
	cat spl/u-boot-spl.bin >> idbloader.img
	bbnote "${PN}: Generating ${UBOOT_ITB}"
	./make.sh itb
}

do_deploy_append() {
	cd ${B}

	for binary in ${UBOOT_IMAGES};do
		[ -f "${binary}" ] || continue
		install "${binary}" "${DEPLOYDIR}/${binary}-${PV}"
		ln -sf "${binary}-${PV}" "${DEPLOYDIR}/${binary}"
	done
}
