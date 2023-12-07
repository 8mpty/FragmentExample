package com.empty.fragmentexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : Fragment() {
    private lateinit var webView: WebView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_web, container, false)
        webView = view.findViewById(R.id.webview)
        return view
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // You can customize the WebView settings here if needed
        webView.settings.javaScriptEnabled = true

        // Load a web page example
        webView.loadUrl("https://m.youtube.com/")
        webView.setWebViewClient(WebViewClient())
    }
}