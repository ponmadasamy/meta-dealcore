# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

PACKAGECONFIG_GL   = "gles2"
PACKAGECONFIG_FONTS = "fontconfig"
PACKAGECONFIG_append += " \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'libinput eglfs', d)} \
	kms \
	tools \
	tslib \
	sql-sqlite2 sql-sqlite \
	vulkan \
"
IMAGE_INSTALL_append += " \
	tslib \
	sqlite sqlite3 sqlite-orm \
	vulkan-tools vulkan-demos \
"
