#!/usr/bin/env bash

# Start IntelliJ IDEA inside nix-shell in detached mode
nix-shell --run "nohup idea-community &>/dev/null &"
