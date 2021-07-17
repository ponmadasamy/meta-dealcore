
DESCRIPTION = "Rockchip ALSA config files"
SECTION = "multimedia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=9645f39e9db895a4aa6e02cb57294595"


SRC_URI = "git://${EXTRA}/alsa-config;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"


inherit meson

FILES_${PN} = "*"
