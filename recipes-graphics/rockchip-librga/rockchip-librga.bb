
DESCRIPTION = "Rockchip RGA 2D graphics acceleration library"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libdrm"

SRC_URI = " git://${EXTRA}/linux-rga;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"


inherit meson pkgconfig

EXTRA_OEMESON = "-Dlibdrm=true"
