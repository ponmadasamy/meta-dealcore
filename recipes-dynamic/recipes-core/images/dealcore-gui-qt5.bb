require recipes-core/images/dealcore-gui.bb
# inherit populate_sdk_qt5

DESCRIPTION = "DealCore GUI with QT5"
LICENSE = "MIT"

DEVELOPMENT_FEATURES_append = " \
						qtcreator-debug \
					"

QT_INSTALL = " \
	qtbase	\
	qtdeclarative qtvirtualkeyboard \
	qtmultimedia \
	qtsensors qtlocation \
	qtsystems \
	qtscript qtsvg qtimageformats \
	qt3d qtgraphicaleffects \
	qtconnectivity qtmqtt \
	qtquickcontrols qtquickcontrols2 \
	qt5-opengles2-test \
	packagegroup-fonts-truetype \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'qtwayland', '', d)} \
"

IMAGE_INSTALL_append = " \
    ${QT_INSTALL} \
"