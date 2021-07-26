inherit core-image features_check
# inherit populate_sdk populate_sdk_ext

DESCRIPTION = "DealCore GUI"
LICENSE = "MIT"

DEVELOPMENT_FEATURES = " \
	debug-tweaks eclipse-debug tools-debug tools-profile tools-testapps \
"

PYTHON3_DEFAULTS = " \
    python3-pyserial python3-gevent python3-alembic python3-robotframework-seriallibrary \
    python3-rfc3987 python3-xlrd python3-bandit python3-constantly python3-inflection \
    python3-javaobj-py3 python3-sh python3-pycrypto python3-pyasn1 python3-pydbus python3-wtforms \
    python3-pybluez python3-babel python3-parse-type python3-bitarray python3-django-south \
    python3-pyusb python3-prctl python3-jinja2 python3-werkzeug python3-requests-ftp \
    python3-behave python3-pyparsing python3-pyyaml python3-tzlocal python3-pretend python3-stevedore \
    python3-sijax python3-langtable python3-requests-file python3-crcmod python3-robotframework \
    python3-pint python3-coverage python3-iso8601 python3-ndg-httpsclient python3-yappi python3-twofish \
    python3-speaklater python3-smbus python3-djangorestframework python3-msgpack python3-jsonpointer \
    python3-flask-script python3-cassandra-driver python3-cython python3-ujson python3-aws-iot-device-sdk-python \
    python3-pytest-runner python3-pyiface python3-docutils python3-flask-login python3-markupsafe python3-setuptools-scm \
    python3-semver python3-sdnotify python3-flask-user python3-tornado python3-jsonpatch python3-pexpect \
    python3-progress python3-jsonschema python3-xstatic python3-pyroute2 python3-idna python3-sqlalchemy \
    python3-urllib3 python3-flask-mail python3-asn1crypto python3-pyinotify python3-intervals python3-pyperclip \
    python3-flask-bootstrap python3-pyudev python3-decorator python3-pybind11 python3-pluggy python3-redis \
    python3-passlib python3-dominate python3-ply python3-ntplib python3-serpent python3-wrapt \
    python3-attrs python3-appdirs python3-isort python3-evdev python3-incremental python3-click python3-flask-nav \
    python3-webcolors python3-dateutil python3-blinker python3-hyperlink python3-lxml python3-pylint \
    python3-flask-migrate python3-pytest-tempdir python3-flask-restful python3-feedformatter \
    python3-pyasn1-modules python3-scapy python3-html5lib python3-dnspython python3-automat \
    python3-itsdangerous python3-pandas python3-pyfirmata python3-protobuf  \
    python3-flask-babel python3-anyjson python3-flask-xstatic python3-multidict python3-prompt-toolkit \
    python3-periphery python3-greenlet python3-pytz python3-pyexpect python3-zopeinterface \
    python3-bcrypt python3-xstatic-font-awesome python3-m2crypto python3-parse python3-attr \
    python3-beautifulsoup4 python3-pycodestyle python3-oauthlib python3-grpcio python3-scrypt \
    python3-pyjwt python3-astroid python3-flask-pymongo python3-wcwidth python3-lazy-object-proxy \
    python3-websockets python3-pyzmq python3-pytest python3-chardet python3-vcversioner python3-whoosh \
    python3-pymisp python3-certifi python3-psutil python3-flask-sqlalchemy python3-humanize \
    python3-grpcio-tools python3-configparser python3-strict-rfc3339 python3-paho-mqtt \
    python3-pytest-helpers-namespace python3-flask python3-flask-wtf python3-visitor python3-pynetlinux \
    python3-requests python3-cryptography-vectors python3-spidev python3-pid python3-pymongo \
    python3-future python3-django python3-unidiff python3-webencodings python3-can python3-pyalsaaudio \
    python3-flask-sijax python3-cryptography python3-twisted python3-netaddr python3-pycparser \
    python3-flask-uploads python3-pysocks python3-cffi python3-editor python3-ptyprocess \
    python3-pyopenssl python3-ordered-set python3-simplejson python3-py \
    ${@bb.utils.contains("DISTRO_FEATURES", "systemd", "python3-systemd", "", d)} \
"
#removed python3-pycryptodome python3-pyjks

# No need for webmin test only
PERL_DEFAULTS = " \
    libproc-waitstat-perl libmoo-perl libterm-readkey-perl \
    libunicode-linebreak-perl libcurses-perl libmime-types-perl \
    libmime-charset-perl libio-socket-ssl-perl libio-stringy-perl \
    libtext-iconv-perl libtext-charwidth-perl libtext-diff-perl \
    libtext-wrapi18n-perl liblocale-gettext-perl \
    libextutils-installpaths-perl libextutils-helpers-perl libextutils-parsexs-perl \
    libextutils-config-perl libextutils-cppguess-perl libimport-into-perl \
    libcrypt-openssl-rsa-perl libcrypt-openssl-guess-perl libcrypt-openssl-random-perl \
    libxml-sax-writer-perl libxml-libxml-perl libxml-filter-buffertext-perl \
    adduser \
    libauthen-sasl-perl libnet-ldap-perl libnet-dns-perl \
    libnet-dns-sec-perl libnet-libidn-perl libnet-ssleay-perl \
    libnet-telnet-perl libdevel-globaldestruction-perl libipc-signal-perl \
    librole-tiny-perl \
    libfile-slurp-perl libcapture-tiny-perl \
    po4a \
    libstrictures-perl libtest-harness-perl libsub-exporter-progressive-perl \
    libclass-method-modifiers-perl libhtml-parser-perl libhtml-tree-perl \
    libmodule-pluggable-perl libmodule-build-perl libmodule-runtime-perl \
    libmodule-build-tiny-perl libcgi-perl libalgorithm-diff-perl \
    libdbd-sqlite-perl libdigest-sha1-perl libdigest-hmac-perl \
"
#removed libencode-perl libencode-locale-perl  libdata-hexdump-perl

IMAGE_FEATURES_append = " \
	${DEVELOPMENT_FEATURES} \
	splash \
	package-management ssh-server-openssh \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11-base x11-sato', '', d)} \
"

IMAGE_INSTALL_append = " \
	packagegroup-core-full-cmdline \
	packagegroup-tools-bluetooth \
	bash-completion nano minicom \
	ltp perf \
	glmark2 \
	i2c-tools usbutils pciutils openssh-sftp-server rsync \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'xterm', '', d)} \
	${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'weston-init weston-xwayland', '', d)} \
	${PYTHON3_DEFAULTS} \
	${PERL_DEFAULTS} \
	${MACHINE_EXTRA_RRECOMMENDS} \
	packagegroup-meta-webserver-webadmin \
"
