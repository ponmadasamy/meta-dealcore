require recipes-core/images/core-image-minimal-xfce.bb
require dealcore-base.bb

DESCRIPTION = "DealCore XFCE image."


export IMAGE_BASENAME = "dealcore-xfce"


SYSTEMD_DEFAULT_TARGET = "graphical.target"