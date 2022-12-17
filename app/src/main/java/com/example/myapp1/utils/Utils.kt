package com.example.myapp1.utils

import android.app.Activity
import android.content.res.Configuration

open class Utils {

    open fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }
}