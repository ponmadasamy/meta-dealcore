require conf/distro/poky.conf

DISTRO = "dealcore-base"
DISTRO_NAME = "DealCore Base"
SDK_VENDOR = "-dealcoresdk"

MAINTAINER = "Ponmadasamy Muthuraj<ponmadasamy@live.com>"

TARGET_VENDOR = "-duragon"

DISTRO_FEATURES_append = " systemd egl"
DISTRO_FEATURES_remove = "pcmcia sysvint gl multiarch x11 wayland nfc usbgadget vulkan"
DISTRO_FEATURES_BACKFILL_CONSIDERED_append = " sysvinit systemd x11 wayland"
VIRTUAL-RUNTIME_init_manager = " systemd"
