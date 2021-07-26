require dealcore-openwrt-luci.bb
# inherit populate_sdk_qt5

DESCRIPTION = "DealCore OpenWrt with QT5"
LICENSE = "MIT"

DEVELOPMENT_FEATURES_append = " \
						qtcreator-debug \
					"
                    					
QT_INSTALL = " \
	qtbase	\
	qtsensors \
	qtsystems \
	qtscript \
	qtconnectivity \
	qtlocation \
	qtmqtt \
"

IMAGE_INSTALL_append = " \
    ${QT_INSTALL} \
"