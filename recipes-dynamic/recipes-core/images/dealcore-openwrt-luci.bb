
DESCRIPTION = "DealCore OpenWrt"
LICENSE = "MIT"

inherit core-image openwrt openwrt-kmods openwrt-services
# inherit populate_sdk populate_sdk_ext

DEVELOPMENT_FEATURES = " \
	debug-tweaks eclipse-debug tools-debug \
"

PYTHON3_DEFAULTS = " \
	python3 \
	python3-pip python3-setuptools \
	python3-requests \
	python3-testtools python3-extras \
"

IMAGE_FEATURES_append = " \
	${DEVELOPMENT_FEATURES} \
	ssh-server-openssh \
"

IMAGE_INSTALL_append = " \
    packagegroup-core-boot \
    packagegroup-openwrt-base \
    packagegroup-openwrt-full \
	packagegroup-tools-bluetooth \
	bash-completion nano minicom \
	i2c-tools usbutils pciutils openssh-sftp-server rsync \
    make-ext4fs ugps usbmode \
    ${MACHINE_EXTRA_RDEPENDS} \
    ${CORE_IMAGE_EXTRA_INSTALL} \
	${PYTHON3_DEFAULTS} \
	${MACHINE_EXTRA_RRECOMMENDS} \
"
