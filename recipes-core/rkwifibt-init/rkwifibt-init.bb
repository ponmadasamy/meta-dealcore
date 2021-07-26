DESCRIPTION = "Rockchip WIFI/BT Init Tool"
SECTION = "net"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
	file://rkwifibt-init.c \
"

S = "${WORKDIR}"

RDEPENDS_{PN} = " \
	brcm-tools \
"

do_compile_append() {
	${CC} ${CFLAGS} ${LDFLAGS} rkwifibt-init.c -o rkwifibt-init
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 rkwifibt-init ${D}${bindir}
}
