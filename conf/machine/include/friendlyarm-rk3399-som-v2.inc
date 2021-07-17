
require rk3399.inc 
require rk3399_image.inc

MACHINE = "friendlyarm-rk3399-som-v2"

RK_WIFIBT_FIRMWARES = " \
	rkwifibt-firmware-ap6356-wifi \
	rkwifibt-firmware-ap6356-bt \
	rkwifi-init \
"

MACHINE_EXTRA_RRECOMMENDS_append = " \
	${RK_WIFIBT_FIRMWARES} \
	kernel-modules \
"
#	linux-firmware-rk-cdndp

# For Wireless
IMAGE_INSTALL_append = " \
	iw wpa-supplicant bluez5 \
	${MACHINE_EXTRA_RRECOMMENDS} \
"

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
PREFERRED_PROVIDER_virtual/xserver-xf86 = "xserver-xorg"

# PREFERRED_PROVIDER_virtual/xserver = "rockchip-xserver-xorg"
# PREFERRED_PROVIDER_virtual/xserver-xf86 = "rockchip-xserver-xorg"
# PREFERRED_VERSION_rockchip-xserver-xorg = "1.20.4"

# ROCKCHIP-XSERVER-XORG = " \
#	rockchip-xserver-xorg-extension-dri \
#	rockchip-xserver-xorg-extension-dri2 \
#	rockchip-xserver-xorg-extension-glx \
# "

# XSERVER = " \
#		rockchip-xserver-xorg \
#		xf86-input-libinput \
#		${ROCKCHIP-XSERVER-XORG} \
# "

# PACKAGECONFIG_append_pn-rockchip-xserver = " "