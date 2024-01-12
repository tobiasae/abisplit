package com.example.abisplit

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.version_code).text = getBuildNumber().toArchitecture()
    }

    private fun getBuildNumber(): Int {
        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            if (packageInfo != null) {
                return packageInfo.versionCode
            }
        } catch (x: PackageManager.NameNotFoundException) {
            Log.e("MainActivity", x.message ?: "")
        }
        return 0
    }

    private fun Int.toArchitecture() = when (this) {
        1 -> "universal"
        2 -> "armeabi-v7a"
        3 -> "arm64-v8a"
        4 -> "x86"
        5 -> "x86_64"
        else -> "unknown"
    }
}