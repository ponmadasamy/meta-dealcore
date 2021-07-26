
require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

SRC_URI = "git://${WORKSPACE}/kernel-4.19;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

do_install_armmultilib_prepend() {
	touch ${D}${includedir}/asm/bpf_perf_event.h
}
