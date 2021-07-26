# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

PACKAGECONFIG_GL   = ""
PACKAGECONFIG_FONTS = ""
PACKAGECONFIG_append = " \
	${@bb.utils.contains('MACHINE_FEATURES', 'display', ' kms gbm eglfs gles2 linuxfb tslib widgets libinput', 'no-opengl', d)} \
	tools \
	fontconfig \
	sql-sqlite sql-sqlite2 \
	openssl \
	${@bb.utils.contains('DISTRO_FEATURES', 'vulkan', 'vulkan', '', d)} \
"
PACKAGECONFIG_remove = " \
	${@bb.utils.contains('MACHINE_FEATURES', 'display', '', 'kms gbm eglfs gles2 linuxfb tslib widgets libinput', d)} \
"

PACKAGECONFIG_append_pn-gstreamer1.0-plugins-good = " qt5"
