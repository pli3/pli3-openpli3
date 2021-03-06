DESCRIPTION = "GNOME library for reading .desktop files"
SECTION = "x11/gnome"
LICENSE = "GPLv2 & LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://COPYING.LIB;md5=5f30f0716dfdd0d91eb439ebec522ec2"

PR = "r4"

inherit gnome pkgconfig

SRC_URI[archive.md5sum] = "5c80d628a240eb9d9ff78913b31f2f67"
SRC_URI[archive.sha256sum] = "55cbecf67efe1fa1e57ac966520a7c46d799c8ba3c652a1219f60cafccb3739d"

DEPENDS += "gconf libxrandr virtual/libx11 gtk+ glib-2.0 gnome-doc-utils"

EXTRA_OECONF = "--disable-scrollkeeper --disable-desktop-docs"

do_configure_prepend () {
	cp ${STAGING_DATADIR}/gnome-common/data/omf.make ${S}
	sed -i -e s:^#!@PYTHON@:#!${bindir}/python: ${S}/gnome-about/gnome-about.in
}

PACKAGES =+ "libgnome-desktop"
FILES_libgnome-desktop = "${libdir}/lib*${SOLIBS} ${datadir}/libgnome-desktop/pnp.ids"
FILES_${PN} += "${datadir}/gnome-about"

# for gnome-about
RRECOMMENDS_${PN} += "python-pygtk python-pycairo"

