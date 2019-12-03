@echo off

mvn clean package -pl phynos-web/phynos-api -am -Dmaven.test.skip=true -Pprod

pause >nul