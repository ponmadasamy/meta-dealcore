# Copyright (c) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

require conf/machine/include/soc-family.inc
include conf/machine/include/arm/arch-armv8a.inc

SOC_FAMILY = "rk3399"
DEFAULTTUNE_rk3399 = "aarch64"

MACHINE_FEATURES_remove = "qemu-usermode"
MACHINE_FEATURES_BACKFILL_CONSIDERED_append = "qemu-usermode"

MALI_GPU := "midgard-t86x"
MALI_VERSION := "r18p0"

RK_ISP_VERSION := "1"

SERIAL_CONSOLES = "1500000;ttyFIQ0"
KERNEL_IMAGETYPE ?= "Image"
MACHINEOVERRIDES .= ":rockchip"

PREFERRED_PROVIDER_virtual/kernel = "rockchip-linux"
PREFERRED_VERSION_rockchip-linux = "4.4%"
LINUXLIBCVERSION = "rockchip-linux-4.4%"

RK_USE_COMPRESSED_KERNEL = "0"

PREFERRED_PROVIDER_virtual/bootloader = "rockchip-uboot"
PREFERRED_VERSION_rockchip-uboot = "2017.09"

# Custom kernel might not support newest python
PACKAGECONFIG_remove_pn-perf += " scripting"

PREFERRED_PROVIDER_virtual/libgl = "mesa-gl"
PREFERRED_PROVIDER_virtual/mesa = "mesa-gl"
PREFERRED_PROVIDER_virtual/egl = "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles1 = "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles2 = "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgles3 = "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libopencl = "rockchip-libmali"
PREFERRED_PROVIDER_virtual/libgbm = "rockchip-libmali"

DEPENDS_append_pn-mesa-gl += " rockchip-libmali"

PREFERRED_RPROVIDER_lib32-initd-functions = "lib32-initscripts"

# Workaround: libmali.so provided by rk having no SONAME field in it
# so add it to fix rdepends problems
ASSUME_SHLIBS += "libEGL.so:rockchip-libmali"
ASSUME_SHLIBS += "libGLESv1_CM.so:rockchip-libmali"
ASSUME_SHLIBS += "libGLESv2.so:rockchip-libmali"
ASSUME_SHLIBS += "libOpenCL.so:rockchip-libmali"
ASSUME_SHLIBS += "libgbm.so:rockchip-libmali"
ASSUME_SHLIBS += "libwayland-egl.so:rockchip-libmali"
