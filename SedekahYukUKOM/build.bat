@echo off
echo ========================================
echo Sedekah Yuk - Build Script
echo ========================================
echo.

REM Check if JDK 17 exists
set JDK17_PATH=C:\Program Files\Eclipse Adoptium\jdk-17.0.13.11-hotspot
set JDK17_PATH_ALT=C:\Program Files\Java\jdk-17

if exist "%JDK17_PATH%" (
    echo [INFO] Using JDK 17 from Eclipse Adoptium
    set JAVA_HOME=%JDK17_PATH%
    goto :build
)

if exist "%JDK17_PATH_ALT%" (
    echo [INFO] Using JDK 17 from Oracle
    set JAVA_HOME=%JDK17_PATH_ALT%
    goto :build
)

REM Check if JDK 11 exists
set JDK11_PATH=C:\Program Files\Java\jdk-11

if exist "%JDK11_PATH%" (
    echo [INFO] Using JDK 11
    set JAVA_HOME=%JDK11_PATH%
    goto :build
)

REM If no compatible JDK found
echo [ERROR] JDK 11 atau 17 tidak ditemukan!
echo.
echo Silakan install JDK 17 dari:
echo https://adoptium.net/temurin/releases/?version=17
echo.
echo Atau gunakan Android Studio untuk build project.
echo.
pause
exit /b 1

:build
echo [INFO] JAVA_HOME: %JAVA_HOME%
echo.
echo [INFO] Membersihkan build sebelumnya...
call gradlew.bat clean

echo.
echo [INFO] Building APK Debug...
call gradlew.bat assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo [SUCCESS] Build berhasil!
    echo ========================================
    echo.
    echo APK tersimpan di:
    echo %CD%\app\build\outputs\apk\debug\app-debug.apk
    echo.
    echo Anda bisa install APK ini ke device Android.
    echo.
) else (
    echo.
    echo ========================================
    echo [ERROR] Build gagal!
    echo ========================================
    echo.
    echo Coba jalankan dengan Android Studio.
    echo.
)

pause
