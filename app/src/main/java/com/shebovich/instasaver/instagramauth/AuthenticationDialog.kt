package com.shebovich.instasaver.instagramauth

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.shebovich.instasaver.R


class AuthenticationDialog(context: Context, listener: AuthenticationListener) :
    Dialog(context) {
    private val request_url: String
    private lateinit var  redirect_url: String
    private val listener: AuthenticationListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_instagram)
        initializeWebView()
    }

    private fun initializeWebView() {
        val webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(request_url)
        webView.webViewClient = webViewClient
    }

    var webViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith(redirect_url)) {
                dismiss()
                return true
            }
            return false
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            if (url.contains("access_token=")) {
                val uri = Uri.parse(url)
                var access_token = uri.encodedFragment
                access_token = access_token!!.substring(access_token.lastIndexOf("=") + 1)
                Log.e("access_token", access_token)
                listener.onTokenReceived(access_token)
                dismiss()
            } else if (url.contains("?error")) {
                Log.e("access_token", "getting error fetching access token")
                dismiss()
            }
        }
    }

    init {
        this.listener = listener
        redirect_url = context.resources.getString(R.string.callback_url)
        request_url = context.resources.getString(R.string.base_url) +
                "oauth/authorize/?client_id=" +
                context.resources.getString(R.string.client_id) +
                "&redirect_uri=" + redirect_url +
                "&response_type=token&display=touch&scope=public_content"
    }
}

