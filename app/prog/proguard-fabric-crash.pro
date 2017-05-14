# fabric crashly proguard rules
# https://docs.fabric.io/android/crashlytics/dex-and-proguard.html#exclude-crashlytics-with-proguard

-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**