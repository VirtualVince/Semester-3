{ pkgs ? import <nixpkgs> { } }:
pkgs.mkShell {
  buildInputs = [
    pkgs.xorg.libXxf86vm
    pkgs.mesa
    pkgs.glib
    pkgs.gtk3
    (pkgs.jdk21.override { enableJavaFX = true; })
  ];

  shellHook = ''
    export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${pkgs.xorg.libXxf86vm}/lib
    export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${pkgs.mesa}/lib
    export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${pkgs.xorg.libXtst}/lib
    export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:${pkgs.glib.out}/lib
    export LIBGL_DRIVERS_PATH=${pkgs.mesa}/lib/dri
  '';
}
