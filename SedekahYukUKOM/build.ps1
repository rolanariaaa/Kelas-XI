# Sedekah Yuk - Build Script untuk PowerShell

Write-Host "========================================" -ForegroundColor Green
Write-Host "Sedekah Yuk - Build Script" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

# Function to check if path exists
function Test-JdkPath {
    param($path)
    return Test-Path $path
}

# Check JDK 17
$jdk17Paths = @(
    "C:\Program Files\Eclipse Adoptium\jdk-17.0.13.11-hotspot",
    "C:\Program Files\Java\jdk-17"
)

$javaHome = $null

foreach ($path in $jdk17Paths) {
    if (Test-JdkPath $path) {
        Write-Host "[INFO] Using JDK 17: $path" -ForegroundColor Cyan
        $env:JAVA_HOME = $path
        $env:PATH = "$path\bin;$env:PATH"
        $javaHome = $path
        break
    }
}

# If JDK 17 not found, check JDK 11
if (-not $javaHome) {
    $jdk11Path = "C:\Program Files\Java\jdk-11"
    if (Test-JdkPath $jdk11Path) {
        Write-Host "[INFO] Using JDK 11: $jdk11Path" -ForegroundColor Cyan
        $env:JAVA_HOME = $jdk11Path
        $env:PATH = "$jdk11Path\bin;$env:PATH"
        $javaHome = $jdk11Path
    }
}

# If no compatible JDK found
if (-not $javaHome) {
    Write-Host "[ERROR] JDK 11 atau 17 tidak ditemukan!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Silakan install JDK 17 dari:" -ForegroundColor Yellow
    Write-Host "https://adoptium.net/temurin/releases/?version=17" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Atau gunakan Android Studio untuk build project." -ForegroundColor Yellow
    Write-Host ""
    pause
    exit 1
}

Write-Host "[INFO] JAVA_HOME: $env:JAVA_HOME" -ForegroundColor Cyan
Write-Host ""

Write-Host "[INFO] Membersihkan build sebelumnya..." -ForegroundColor Cyan
& .\gradlew.bat clean

Write-Host ""
Write-Host "[INFO] Building APK Debug..." -ForegroundColor Cyan
& .\gradlew.bat assembleDebug

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "[SUCCESS] Build berhasil!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "APK tersimpan di:" -ForegroundColor Cyan
    Write-Host "$PWD\app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Yellow
    Write-Host ""
    Write-Host "Anda bisa install APK ini ke device Android." -ForegroundColor Cyan
    Write-Host ""
} else {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "[ERROR] Build gagal!" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
    Write-Host ""
    Write-Host "Coba jalankan dengan Android Studio." -ForegroundColor Yellow
    Write-Host ""
}

pause
