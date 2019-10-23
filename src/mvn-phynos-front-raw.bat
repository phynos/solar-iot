@echo off

mvn clean package -pl phynos-front/phynos-front-raw -am -Dmaven.test.skip=true -Pprod

pause >nul