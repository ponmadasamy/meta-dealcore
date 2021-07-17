
DESCRIPTION = "DealCore GUI"
LICENSE = "MIT"

inherit core-image features_check
# inherit populate_sdk

DISTRO_FEATURES_remove = "sysvinit"
DISTRO_FEATURES_append = " systemd"
VIRTUAL-RUNTIME_init_manager = "systemd"

DEVELOPMENT_FEATURES = " \
	debug-tweaks eclipse-debug qtcreator-debug tools-debug tools-profile tools-testapps \
"

IMAGE_FEATURES_append = " \
	${DEVELOPMENT_FEATURES} \
	splash package-management ssh-server-openssh \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11-base x11-sato', '', d)} \
"

IMAGE_INSTALL_append = " \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	bash-completion nano minicom \
	ltp perf glmark2 \
	i2c-tools usbutils pciutils openssh-sftp-server rsync \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xterm', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init weston-xwayland', '', d)} \
	${@bb.utils.contains('MACHINE_EXTRA_RRECOMMENDS', 'rkwifi-init', 'brcm-tools', '', d)} \
"

# IMAGE_INSTALL_append = " \
# 	${ROCKCHIP-XSERVER-XORG} \
# "
# VIRTUAL-RUNTIME_graphical_init_manager