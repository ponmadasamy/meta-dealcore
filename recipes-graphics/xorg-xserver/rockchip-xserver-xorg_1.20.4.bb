require recipes-graphics/xorg-xserver/xserver-xorg.inc

DEPENDS += "util-macros-native font-util-native xtrans-native rockchip-librga"

SRC_URI = " git://${EXTRA}/xorg-xserver;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://20-modesetting.conf"

CFLAGS += "-fcommon"

# These extensions are now integrated into the server, so declare the migration
# path for in-place upgrades.

RREPLACES_${PN} =  "${PN}-extension-dri \
                    ${PN}-extension-dri2 \
                    ${PN}-extension-record \
                    ${PN}-extension-extmod \
                    ${PN}-extension-dbe \
                    ${PN}-extension-glx \
                   "
RPROVIDES_${PN} =  "${PN}-extension-dri \
                    ${PN}-extension-dri2 \
                    ${PN}-extension-record \
                    ${PN}-extension-extmod \
                    ${PN}-extension-dbe \
                    ${PN}-extension-glx \
                   "
RCONFLICTS_${PN} = "${PN}-extension-dri \
                    ${PN}-extension-dri2 \
                    ${PN}-extension-record \
                    ${PN}-extension-extmod \
                    ${PN}-extension-dbe \
                    ${PN}-extension-glx \
                   "

do_configure_prepend() {
    NOCONFIGURE="yes" ${S}/autogen.sh
}

do_install_append() {
    install -d ${D}${datadir}/X11/xorg.conf.d
    install -m 0755 ${WORKDIR}/20-modesetting.conf \
        ${D}${datadir}/X11/xorg.conf.d/20-modesetting.conf
}
