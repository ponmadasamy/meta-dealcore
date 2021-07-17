# Copyright (C) 2020, Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

# Some opengl[es] libraries are multithreaded.
LDFLAGS += "-pthread"

PACKAGECONFIG_append += " \
	${@bb.utils.contains('DISTRO_FEATURES', 'x11 opengl', 'x11-gles2', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland opengl', 'wayland-gles2', '', d)} \
	drm-gles2 \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland-protocols', '', d)} \
"