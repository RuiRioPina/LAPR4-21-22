#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
# shellcheck disable=SC2125
export BASE_CP=base.app.agvmanager.server/target/base.app.agvmanager.console-1.4.0-SNAPSHOT.jar:base.app.agvmanager.server/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP eapli.base.app.agvmanager.server.TcpServerAGVManager