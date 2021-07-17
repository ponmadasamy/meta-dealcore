
PACKAGE_INSTALL = "initramfs-boot busybox base-files ${ROOTFS_BOOTSTRAP_INSTALL}"

# Do not pollute the initrd image with rootfs features
IMAGE_FEATURES = ""

export IMAGE_BASENAME = "dealcore-initramfs"
IMAGE_LINGUAS = ""

LICENSE = "MIT"
IMAGE_FSTYPES = "cpio.xz"

inherit core-image

IMAGE_ROOTFS_SIZE = "8192"
