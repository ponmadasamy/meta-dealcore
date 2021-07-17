DESCRIPTION = "RKWIFI Init Tool"
SECTION = "net"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = " \
	file://rk_wifi_init.c \
"

S = "${WORKDIR}"

RDEPENDS_{PN} = " \
	brcm-tools \
"

do_compile_append() {
	${CC} ${CFLAGS} ${LDFLAGS} rk_wifi_init.c -o rk_wifi_init
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 rk_wifi_init ${D}${bindir}
}
