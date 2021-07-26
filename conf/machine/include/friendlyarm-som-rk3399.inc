
require rk3399.inc 
require rk3399_image.inc

MACHINE = "friendlyarm-som-rk3399"

KERNEL_DEVICETREE = "rockchip/friendlyarm-rk3399-som.dtb"
KBUILD_DEFCONFIG = "friendlyarm-rk3399-som_defconfig"

UBOOT_MACHINE = "friendlyarm-rk3399-som_defconfig"

MACHINE_EXTRA_RRECOMMENDS_append = " \
	rkwifibt-firmware-ap6356-wifi rkwifibt-firmware-ap6356-bt rkwifibt-init brcm-tools \
	iw wpa-supplicant bluez5 \
	kernel-modules \
	alsa-utils rockchip-alsa-config pulseaudio-server alsa-plugins-pulseaudio-conf \
"

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
PREFERRED_PROVIDER_virtual/xserver-xf86 = "xserver-xorg"
