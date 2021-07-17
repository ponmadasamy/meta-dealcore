require dealcore-gui.bb

# inherit populate_sdk_qt5

DESCRIPTION = "DealCore GUI with QT5"
LICENSE = "MIT"

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
	qtgraphicaleffects \
	qtconnectivity \
	qtlocation \
	qtquickcontrols \
	qtquickcontrols2 \
	qt5-opengles2-test \
	packagegroup-fonts-truetype \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
	qtvirtualkeyboard \
	qtmqtt \
"

IMAGE_INSTALL_append = " \
    ${QT_INSTALL} \
"