
require conf/machine/include/rk3399.inc 
require conf/machine/include/rockchip_4.4.inc

MACHINE = "friendlyarm-rk3399-som-v2"

KERNEL_DEVICETREE = "rockchip/friendlyarm-rk3399-som-v2.dtb"
KBUILD_DEFCONFIG ?= "friendlyarm-rk3399-som-v2_defconfig"

UBOOT_MACHINE = "friendlyarm-rk3399-som-v2_defconfig"

RK_WIFIBT_FIRMWARES = " \
	rkwifibt-firmware-ap6356-wifi \
	rkwifibt-firmware-ap6356-bt \
	brcm-tools \
"

MACHINE_EXTRA_RRECOMMENDS_append += " \
	linux-firmware-rk-cdndp \
"

PREFERRED_PROVIDER_virtual/kernel = "linux-rockchip"

# Workaround: libmali.so provided by rk having no SONAME field in it
# so add it to fix rdepends problems
ASSUME_SHLIBS += "libEGL.so:rockchip-libmali"
ASSUME_SHLIBS += "libGLESv1_CM.so:rockchip-libmali"
ASSUME_SHLIBS += "libGLESv2.so:rockchip-libmali"
ASSUME_SHLIBS += "libOpenCL.so:rockchip-libmali"
ASSUME_SHLIBS += "libgbm.so:rockchip-libmali"
ASSUME_SHLIBS += "libwayland-egl.so:rockchip-libmali"

DEFAULTTUNE_rk3399="aarch64"
