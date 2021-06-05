
FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}:"

SRC_URI += " \
	file://0001.patch \
"
SRC_URI_remove += " \
	file://0001-Support-rebooting-with-arg.patch \
	"