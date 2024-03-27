@echo off
if "%1"=="" (
    echo Usage: %0 [arg1] [arg2]
    exit /b 1
)
if "%2"=="" (
    echo Usage: %0 [arg1] [arg2]
    exit /b 1
)
java -jar roboNav.jar %1 %2
