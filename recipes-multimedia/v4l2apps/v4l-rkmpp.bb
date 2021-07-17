
DESCRIPTION = "A V4L2 plugin that wraps rockchip-mpp for the chromium's V4L2 VDA"

SECTION = "libs"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d749e86a105281d7a44c2328acebc4b0"

SRC_URI = "git://${EXTRA}/libv4l-rkmpp;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"

DEPENDS = "rockchip-mpp rockchip-librga libv4l"

inherit autotools pkgconfig

FILES_${PN} = "${libdir}/libv4l/plugins/*.so"
