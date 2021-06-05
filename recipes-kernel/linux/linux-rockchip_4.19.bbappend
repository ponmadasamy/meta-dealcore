
SRC_URI = "git://${WORKSPACE}/kernel-4.19;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"
KBRANCH = "HEAD"

KERNEL_VERSION_SANITY_SKIP = "1"
LINUX_VERSION ?= "4.19"

SRC_URI_append += " \
	file://cgroups.cfg \
	${@bb.utils.contains('IMAGE_FSTYPES', 'ext4', ' file://ext4.cfg', '', d)} \
"

SRC_URI_remove += " \
	file://0001-init-do_mounts.c-Retry-all-fs-after-failed-to-mount-.patch \
	file://0002-perf-bench-Share-some-global-variables-to-fix-build-.patch \
	file://0003-perf-parse-events-Use-asprintf-instead-of-strncpy-to.patch \
	file://0004-perf-cs-etm-Move-definition-of-traceid_list-global-v.patch \
	file://0005-perf-tests-bp_account-Make-global-variable-static.patch \
	file://0006-libtraceevent-Fix-build-with-binutils-2.35.patch \
"