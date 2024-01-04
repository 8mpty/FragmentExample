package com.empty.fragmentexample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.fragment_web.*
import org.mozilla.geckoview.GeckoResult
import org.mozilla.geckoview.GeckoSession
import org.mozilla.geckoview.GeckoSessionSettings
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.WebExtensionController

class WebFragment : Fragment(R.layout.fragment_web) {
    private lateinit var session: GeckoSession
    private lateinit var runtime: GeckoRuntime
    private val websitesTest = arrayListOf(
        "about:buildconfig",
        "https://m.youtube.com",
        "https://music.youtube.com"
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val settings = GeckoSessionSettings.Builder()
            .allowJavascript(true)
            //.usePrivateMode(true)
            .build()
        session = GeckoSession(settings)
        session?.permissionDelegate = object : GeckoSession.PermissionDelegate {
            override fun onContentPermissionRequest(
                session: GeckoSession,
                perm: GeckoSession.PermissionDelegate.ContentPermission
            ): GeckoResult<Int>? {
                return super.onContentPermissionRequest(session, perm)
            }
        }
        runtime = Runtime.getGeckoRuntime(requireContext())
        runtime.webExtensionController
            .install("https://github.com/gorhill/uBlock/releases/download/1.54.0/uBlock0_1.54.0.firefox.signed.xpi")

        session.open(runtime)
        webview.setSession(session)
        session.loadUri(websitesTest[1])
    }
}