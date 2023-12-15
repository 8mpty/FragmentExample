package com.empty.fragmentexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.webkit.WebViewClient
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.mainView
import kotlinx.android.synthetic.main.activity_main.toolbarLay
import kotlinx.android.synthetic.main.fragment_web.*
import kotlinx.android.synthetic.main.fragment_web.view.webview

class WebFragment : Fragment(R.layout.fragment_web) {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbarLay: LinearLayout = requireActivity().findViewById(R.id.toolbarLay)
        webview.settings.apply {
            javaScriptEnabled = true
        }
        webview.setOnLongClickListener{
            if(toolbarLay.visibility != View.VISIBLE){
                toolbarLay.visibility = View.VISIBLE
            }
            false
        }

        webview.loadUrl("https://m.youtube.com/")

        webview.webViewClient = WebViewClient()
    }
}