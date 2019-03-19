#!/bin/sh

keyname=wintech
password='ha7svu6e'
keytool -genkey -keyalg RSA -keysize 1024 -validity 36500 -dname "CN=wintech, OU=wintech,O=wintech, L=chengdu, ST=chengdu, C=CN" -alias ${keyname} -keypass ${password} -keystore ${keyname}.jks -storepass ${password}
echo ${password} | keytool -importkeystore -srckeystore ${keyname}.jks -destkeystore ${keyname}.jks -deststoretype pkcs12
mv ${keyname}.jks ./core/src/main/resources/
rm -f ${keyname}.jks.old
