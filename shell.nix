{ pkgs ? import <nixpkgs> {} }:
pkgs.mkShell {
  buildInputs = with pkgs; [
    openjdk17        # Java JDK
    jetbrains.idea-community # IntelliJ IDEA (or use 'idea-ultimate' for Ultimate edition)
    maven
  ];

  # Optional: Set environment variables for IntelliJ
  shellHook = ''
    export JAVA_HOME=${pkgs.openjdk17}/lib/openjdk
    export PATH=$JAVA_HOME/bin:$PATH
  '';
}
