{ pkgs ? import <nixpkgs> {} }:
pkgs.mkShell {
  buildInputs = with pkgs; [
    openjdk17        # Java JDK
    maven
  ];

  # Optional: Set environment variables for IntelliJ
  shellHook = ''
    export JAVA_HOME=${pkgs.openjdk17}/lib/openjdk
    export PATH=$JAVA_HOME/bin:$PATH
  '';
}
