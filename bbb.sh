echo "---start----"
gradle aDebug
adb install -r app/build/outputs/apk/app-debug.apk
adb shell am start -n com.zgb.plmm/com.zgb.plmm.MainActivity
echo "----end-------"
