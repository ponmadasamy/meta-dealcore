

require recipes-kernel/linux/linux-yocto.inc
inherit python3-dir

LINUX_VERSION = "4.19"
KERNEL_VERSION_SANITY_SKIP = "1"

DEPENDS += "openssl-native lz4-native ${PYTHON_PN}-native"

# LINUX_VERSION_EXTENSION ?= "-rockchip-${LINUX_KERNEL_TYPE}"
LINUX_VERSION_EXTENSION = ""

# Hack for rockchip style images
ROCKCHIP_KERNEL_IMAGES = "kernel.img resource.img boot.img zboot.img"
KERNEL_IMAGETYPES += "${ROCKCHIP_KERNEL_IMAGES}"
python () {
    if not d.getVar('KERNEL_DEVICETREE'):
        raise bb.parse.SkipPackage('KERNEL_DEVICETREE is not specified!')

    # Use rockchip stype target, which is '<dts(w/o suffix)>.img'
    d.setVar('KERNEL_IMAGETYPE_FOR_MAKE', ' ' + d.getVar('KERNEL_DEVICETREE').replace('rockchip/', '').replace('.dtb', '.img'));
}


LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

# SRC_URI = "git://${WORKSPACE}/kernel-4.19;protocol=file;usehead=1"
SRC_URI = "${DEALCORE_GIT}/rockchip-linux-4.19.git;branch=dealcore;"
SRCREV = "${AUTOREV}"
KBRANCH = "HEAD"


SRC_URI_append = " \
    file://ipset.cfg \
    file://bridge.cfg \
	file://cgroups.cfg \
	${@bb.utils.contains('IMAGE_FSTYPES', 'ext4', ' file://ext4.cfg', '', d)} \
"

KCONFIG_MODE ?= "--alldefconfig"

# Make sure we use /usr/bin/env ${PYTHON_PN} for scripts
do_patch_append() {
	for s in `grep -rIl python ${S}/scripts`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env ${PYTHON_PN}|' $s
	done
}

# Force regenerating defconfig
do_kernel_metadata_prepend() {
	rm -f ${WORKDIR}/defconfig
}

# Link rockchip style images
do_install_prepend() {
	for image in ${ROCKCHIP_KERNEL_IMAGES};do
		ln -sf ${B}/${image} ${B}/arch/${ARCH}/boot/
	done

	if [ "x${RK_USE_COMPRESSED_KERNEL}" = "x1" ]; then
		ln -sf zboot.img ${B}/arch/${ARCH}/boot/boot.img
	fi
}

do_deploy_append() {
	cd ${B}
	
	for image in ${ROCKCHIP_KERNEL_IMAGES};do
		[ -f "${image}" ] || continue
		install "${B}/${image}" "${DEPLOYDIR}/${image}-${PV}"
		ln -sf "${image}-${PV}" "${DEPLOYDIR}/${image}"
	done
}