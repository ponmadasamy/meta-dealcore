
PACKAGECONFIG_append += " \
		opencv \
		${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', '', d)}"

PACKAGECONFIG_remove += " \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'gl', d), d)} \
"