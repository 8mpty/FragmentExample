package com.empty.fragmentexample

import android.app.Application
import android.content.Context
import org.mozilla.geckoview.GeckoRuntime

class Runtime : Application() {
    companion object {
        private lateinit var geckoRuntime: GeckoRuntime
        fun getGeckoRuntime(context: Context): GeckoRuntime {
            if (!::geckoRuntime.isInitialized) {
                geckoRuntime = GeckoRuntime.create(context.applicationContext)
            }
            return geckoRuntime
        }
    }
}