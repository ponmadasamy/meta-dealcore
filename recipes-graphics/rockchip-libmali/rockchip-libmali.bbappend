
SRC_URI = "git://${EXTRA}/libmali;protocol=file;usehead=1"
SRCREV = "${AUTOREV}"

SRC_URI_remove = " \
	file://0001-gbm-Define-MALIGBM-macro.patch \
"