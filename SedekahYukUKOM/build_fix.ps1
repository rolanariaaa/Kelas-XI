# Script untuk build aplikasi dengan menghindari error Gradle
Write-Host "=== Build Script untuk SedekahYuk ===" -ForegroundColor Green

# Set environment
$env:GRADLE_USER_HOME = "$env:USERPROFILE\.gradle"
$env:JAVA_TOOL_OPTIONS = "-Dfile.encoding=UTF-8"

Write-Host "`nMenghapus cache build..." -ForegroundColor Yellow
Remove-Item -Path "app\build" -Recurse -Force -ErrorAction SilentlyContinue
Remove-Item -Path "build" -Recurse -Force -ErrorAction SilentlyContinue

Write-Host "`nMenjalankan build..." -ForegroundColor Yellow

# Coba build dengan parameter yang berbeda
try {
    & .\gradlew.bat assembleDebug --no-daemon --no-build-cache --refresh-dependencies 2>&1 | Tee-Object -FilePath "build_log.txt"
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "`n=== BUILD BERHASIL! ===" -ForegroundColor Green
        Write-Host "APK tersimpan di: app\build\outputs\apk\debug\app-debug.apk" -ForegroundColor Cyan
    } else {
        Write-Host "`n=== BUILD GAGAL ===" -ForegroundColor Red
        Write-Host "Log tersimpan di: build_log.txt" -ForegroundColor Yellow
        Write-Host "`nSolusi alternatif:" -ForegroundColor Cyan
        Write-Host "1. Buka project di Android Studio" -ForegroundColor White
        Write-Host "2. Klik menu Build > Rebuild Project" -ForegroundColor White
        Write-Host "3. Atau klik tombol Run (▶️) untuk install langsung ke emulator/device" -ForegroundColor White
    }
} catch {
    Write-Host "`nError: $_" -ForegroundColor Red
}
