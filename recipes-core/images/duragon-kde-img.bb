SUMMARY = "KDE Plasma Mobile image"

IMAGE_FEATURES += "splash package-management hwcodecs x11"

LICENSE = "MIT"

inherit features_check

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
	openjdk-8 \
        ${CORE_IMAGE_BASE_INSTALL} \
        wayland dbus \
        sddm \
        packagegroup-kde-frameworks5 \
        packagegroup-plasma-mobile \
        packagegroup-core-x11-xserver \
        packagegroup-core-x11-utils \
        ${BROWSER_INSTALL} \
        ${QT_INSTALL} \
"

inherit core-image
