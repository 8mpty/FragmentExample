package com.empty.fragmentexample

import android.annotation.SuppressLint
import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.mainView
import kotlinx.android.synthetic.main.activity_main.toolbarLay
import kotlinx.android.synthetic.main.fragment_web.*
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoSessionSettings
import com.empty.fragmentexample.GeckoRuntime

class WebFragment : Fragment(R.layout.fragment_web) {
    @SuppressLint("SetJavaScriptEnabled")
    private lateinit var context: Context
    private lateinit var session: GeckoSession
    private lateinit var runtime: org.mozilla.geckoview.GeckoRuntime
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settings = GeckoSessionSettings.Builder()
            .allowJavascript(true)
            .usePrivateMode(true)
            .build()
        session = GeckoSession(settings)
        runtime = GeckoRuntime.getGeckoRuntime(requireContext())

        session.open(runtime)
        webview.setSession(session)
        webview.isLongClickable = true

        session.loadUri("https://m.youtube.com")
    }
}