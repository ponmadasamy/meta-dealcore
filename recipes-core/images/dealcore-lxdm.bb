
require dealcore-base.bb

DESCRIPTION = "DealCore LXDM Image"
IMAGE_FEATURES_append = " ssh-server-dropbear"

LICENSE = "MIT"

IMAGE_FEATURES += "splash package-management x11-base"

export IMAGE_BASENAME = "dealcore-lxdm"

inherit core-image
