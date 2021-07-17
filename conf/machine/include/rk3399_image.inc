
export RK_ROOTDEV_UUID ?= "614e0000-0000-4b53-8000-1d28000054a9"
export RK_PARTITION_GROW ?= "1"

RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""

IMAGE_FSTYPES_remove += "iso live"
export RK_ROOTFS_TYPE ?= "ext4"

IMAGE_FSTYPES += "${RK_ROOTFS_TYPE} wic"
WKS_FILES = "sdmmc-gptdisk.wks.in"

# Some partitons, e.g. trust, are allowed to be optional.
do_fixup_wks[depends] += " \
	virtual/kernel:do_deploy \
	virtual/bootloader:do_deploy \
"
do_fixup_wks() {
	if [ ! -f "${WKS_FULL_PATH}" ];then
		bbwarn "${WKS_FULL_PATH} missing"
	fi

	IMAGES=$(grep -o "[^=]*\.img" "${WKS_FULL_PATH}")

	for image in ${IMAGES};do
		if [ ! -f "${DEPLOY_DIR_IMAGE}/${image}" ];then
			echo "${image} not provided, ignoring it."
			sed -i "/file=${image}/d" "${WKS_FULL_PATH}"
		fi
	done
}
addtask do_fixup_wks after do_write_wks_template before do_image_wic

IMAGE_POSTPROCESS_COMMAND += "link_rootfs_image;"
link_rootfs_image() {
	ln -sf "${IMAGE_LINK_NAME}.${RK_ROOTFS_TYPE}" \
		"${IMGDEPLOYDIR}/rootfs.img"
}

IMAGE_POSTPROCESS_COMMAND += "gen_rkparameter;"
gen_rkparameter() {
	if [ ! -f "${DEPLOY_DIR_IMAGE}/loader.bin" ];then
		echo "Skip making Rockchip parameter."
		return
	fi

	IMAGE="${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.wic"
	if [ ! -f "${IMAGE}" ];then
		echo "${IMAGE} not found."
		return
	fi

	cd "${IMGDEPLOYDIR}"

	OUT="${IMAGE_LINK_NAME}.parameter"
	ln -sf "${OUT}" parameter

	echo "Generating ${OUT}..."

	echo "# IMAGE_NAME: $(readlink ${IMAGE})" > "${OUT}"
	echo "FIRMWARE_VER: 1.0" >> "${OUT}"
	echo "TYPE: GPT" >> "${OUT}"
	echo -n "CMDLINE: mtdparts=rk29xxnand:" >> "${OUT}"
	sgdisk -p "${IMAGE}" | grep -E "^ +[0-9]" | while read line;do
		NAME=$(echo ${line} | cut -f 7 -d ' ')
		START=$(echo ${line} | cut -f 2 -d ' ')
		END=$(echo ${line} | cut -f 3 -d ' ')
		SIZE=$(expr ${END} - ${START} + 1)
		printf "0x%08x@0x%08x(%s)," ${SIZE} ${START} ${NAME} >> "${OUT}"
	done
	echo >> "${OUT}"

	if [ "$RK_PARTITION_GROW" = "1" ];then
		sed -i "s/[^,]*\(@[^,]*\)),$/-\1:grow)/" "${OUT}"
	fi

	echo "uuid: rootfs=${RK_ROOTDEV_UUID}" >> "${OUT}"
}

IMAGE_POSTPROCESS_COMMAND += "gen_rkupdateimg;"
do_image[depends] += "rk-binary-native:do_populate_sysroot"
gen_rkupdateimg() {
	if [ ! -f "${DEPLOY_DIR_IMAGE}/loader.bin" ];then
		echo "Skip packing Rockchip update image."
		return
	fi

	IMAGE="${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.wic"
	if [ ! -f "${IMAGE}" ];then
		echo "${IMAGE} not found."
		return
	fi

	cd "${IMGDEPLOYDIR}"

	RK_IMAGES="loader.bin uboot.env uboot.img trust.img kernel.img \
			resource.img boot.img"

	RK_IMAGES="loader.bin idbloader.img u-boot.itb boot.img rootfs.img"
	# Create temporary symlinks, because the tool would crash with abs pathes
	for img in ${RK_IMAGES};do
		f="${DEPLOY_DIR_IMAGE}/${img}"
		[ -f "${f}" ] && ln -sf "${f}" "./${img}"
	done

	OUT="${IMAGE_LINK_NAME}.package-file"
	ln -sf "${OUT}" package-file
	
	echo "Generating ${OUT}..."

	echo "# IMAGE_NAME: $(readlink ${IMAGE})" > "${OUT}"
	echo "package-file package-file" >> "${OUT}"
	echo "bootloader idbloader.img" >> "${OUT}"
	echo "parameter parameter" >> "${OUT}"
	echo "loader2 u-boot.itb" >> "${OUT}"
	echo "boot boot.img" >> "${OUT}"
	echo "rootfs rootfs.img" >> "${OUT}"
	
	afptool -pack ./ update.raw.img
	rkImageMaker -RK$(hexdump -s 21 -n 4 -e '4/1 "%c"' loader.bin | rev) \
		loader.bin update.raw.img "${IMAGE_LINK_NAME}.update.img" \
		-os_type:androidos
	ln -sf "${IMAGE_LINK_NAME}.update.img" update.img

	rm -rf ${RK_IMAGES} update.raw.img
}

IMAGE_POSTPROCESS_COMMAND += "link_latest_image;"
link_latest_image() {
	rm -rf "${TOPDIR}/latest"
	ln -sf "${DEPLOY_DIR_IMAGE}" "${TOPDIR}/latest"
}
