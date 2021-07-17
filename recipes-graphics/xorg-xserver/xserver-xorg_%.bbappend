
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS += "util-macros-native font-util-native xtrans-native rockchip-librga"

SRCREV = "${AUTOREV}"
SRC_URI += "git://github.com/JeffyCN/xorg-xserver;nobranch=1;branch=${PV}_2021_03_15;"
SRC_URI_remove = "https://www.x.org/releases//individual/xserver/xorg-server-${PV}.tar.bz2"
S = "${WORKDIR}/git"

SRC_URI += " \
    file://20-modesetting.conf \
    file://10-monitor.conf \
"

do_configure_prepend() {
    NOCONFIGURE="yes" ${S}/autogen.sh
}

do_install_append() {
    install -d ${D}${datadir}/X11/xorg.conf.d

    install -m 0755 ${WORKDIR}/20-modesetting.conf \
        ${D}${datadir}/X11/xorg.conf.d/20-modesetting.conf    

    install -m 0755 ${WORKDIR}/10-monitor.conf \
        ${D}${datadir}/X11/xorg.conf.d/10-monitor.conf
}
