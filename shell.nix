{ pkgs ? import <nixpkgs> {} }:
pkgs.mkShell {
  buildInputs = [
    pkgs.openjdk17        # Java JDK
    pkgs.jetbrains.idea-community # IntelliJ IDEA (or use 'idea-ultimate' for Ultimate edition)
  ];

  # Optional: Set environment variables for IntelliJ
  shellHook = ''
    export JAVA_HOME=${pkgs.openjdk17}/lib/openjdk
    export PATH=$JAVA_HOME/bin:$PATH
  '';
}
