
SRC_URI = "git://${WORKSPACE}/u-boot;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"

SRC_URI_remove += " \
	file://0001-envtools-make-sure-version-timestamp-header-file-are.patch \
	file://0002-Revert-Makefile-enable-Werror-option.patch \
	file://0003-rockchip-vendor-Fix-maybe-uninitialized.patch \
	file://0004-mkimage-rkcommon-Add-rk3326-and-px3se.patch \
	file://0005-scripts-Makefile.lib-remove-overridden-target-obj-he.patch \
	file://0006-Add-target-to-generate-initial-environment.patch \
"