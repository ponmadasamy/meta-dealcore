
require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

inherit auto-patch

inherit freeze-rev

SRC_URI = "git://${WORKSPACE}/kernel-4.19;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

do_install_armmultilib_prepend() {
	touch ${D}${includedir}/asm/bpf_perf_event.h
}
