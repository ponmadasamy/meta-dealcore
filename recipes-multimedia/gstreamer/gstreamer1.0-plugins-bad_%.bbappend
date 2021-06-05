# Copyright (c) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

inherit auto-patch

PACKAGECONFIG_append += " \
		opencv \
		${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"

PACKAGECONFIG_remove += " \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'gl', d), d)} \
"