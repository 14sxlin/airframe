#!/bin/bash

coursier bootstrap org.scalameta:scalafmt-cli_2.12:2.0.1 -r sonatype:releases -o scalafmt --standalone --main org.scalafmt.cli.Cli -f
chmod +x scalafmt
