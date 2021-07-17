SUMMARY = "KDE Plasma Mobile image"

IMAGE_FEATURES += "splash package-management hwcodecs x11"

LICENSE = "MIT"

inherit features_check

# For Audio
IMAGE_INSTALL_append += " alsa-utils rockchip-alsa-config pulseaudio-server alsa-plugins-pulseaudio-conf"
DISTRO_FEATURES_append += " alsa pulseaudio"

# For Multimedia
LICENSE_FLAGS_WHITELIST = "commercial"
IMAGE_INSTALL_append += " gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-rockchip ffmpeg"
IMAGE_INSTALL_append += "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'gstreamer1.0-plugins-bad', '', d)}"
IMAGE_INSTALL_append += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'gstreamer1.0-plugins-bad', '', d)}"
IMAGE_INSTALL_append += " rockchip-rkisp-server rockchip-rkisp-iqfiles"

# For debug
IMAGE_INSTALL_append += " libdrm-tests strace gdb e2fsprogs e2fsprogs-resize2fs io perf openssh-sftp-server"
IMAGE_FEATURES += "ssh-server-openssh tools-debug eclipse-debug tools-profile tools-testapps debug-tweaks qtcreator-debug"

# For adb
IMAGE_INSTALL_append += " android-tools"

# For glmark2
DISTRO_FEATURES_append += " opengl"
IMAGE_INSTALL_append += " glmark2"

# For mali gpu
DISTRO_FEATURES_append += " egl"

BROWSER_INSTALL = " \
        ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'chromium-ozone-wayland libv4l v4l-rkmpp', '', d)} \
        ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'chromium-x11 libv4l v4l-rkmpp', '', d)} \
"

QT_INSTALL = " \
	qtbase	\
	qtdeclarative \
	qtmultimedia \
	qtsvg \
	qtsensors \
	qtimageformats \
	qtsystems \
	qtscript \
	qt3d \
	qtwebengine \
	qtgraphicaleffects \
	qtconnectivity \
	qtlocation \
	qtquickcontrols \
	qtquickcontrols2 \
	qt5-opengles2-test \
	packagegroup-fonts-truetype \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
"

IMAGE_INSTALL = " \
        dvfs-rules resize2fs \
		${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)} \
		openjdk-8 \
        ${CORE_IMAGE_BASE_INSTALL} \
        dbus \
        ${BROWSER_INSTALL} \
        ${QT_INSTALL} \
"

PACKAGECONFIG_pn-wayland ?= "${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'no-egl', '', d)}"

inherit core-image
