
include recipes-multimedia/gstreamer/gstreamer1.0-plugins.inc
include recipes-multimedia/gstreamer/gstreamer1.0-plugins-packaging.inc

DESCRIPTION = "GStreamer 1.0 plugins for Rockchip platforms"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6d1e4aa87f6192354d3de840cf774d93"
DEPENDS += "gstreamer1.0-plugins-base rockchip-mpp"

SRC_URI = "git://${EXTRA}/gstreamer-rockchip;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"


SRC_URI_remove = " \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
    file://gtk-doc-tweaks.patch \
"

inherit gettext autotools pkgconfig

PACKAGECONFIG ??= "mpp ${@bb.utils.filter('DISTRO_FEATURES', 'x11', d)}"

PACKAGECONFIG[mpp]    = "--enable-rockchipmpp,--disable-rockchipmpp,rockchip-mpp"
PACKAGECONFIG[x11]    = "--enable-rkximage,--disable-rkximage,libx11"

EXTRA_OECONF_remove = "--disable-gtk-doc"

delete_pkg_m4() {
	# Delete m4 files which we provide patched versions of but will be
	# ignored if these exist
	rm -f "${S}/common/m4/pkg.m4"
	rm -f "${S}/common/m4/gtk-doc.m4"
}

do_configure[prefuncs] = " delete_pkg_m4"

do_configure() {
    NOCONFIGURE=true ${S}/autogen.sh
    oe_runconf
}
