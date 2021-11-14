package com.onban.kauantapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.onban.kauantapp.R

class WebActivity : AppCompatActivity() {

    private lateinit var mWebSettings: WebSettings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webViewSetting()
        
        val url = intent.getStringExtra("url")
        url?.let {
            findViewById<WebView>(R.id.webView).loadUrl(it)
        }
    }

    private fun webViewSetting() {
        val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient= WebViewClient()
        mWebSettings = webView.settings
        true.also { mWebSettings.javaScriptEnabled = it }
        mWebSettings.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        mWebSettings.javaScriptCanOpenWindowsAutomatically= false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings.loadWithOverviewMode= true // 메타태그 허용 여부
        mWebSettings.useWideViewPort= true // 화면 사이즈 맞추기 허용 여부
        mWebSettings.setSupportZoom(false) // 화면 줌 허용 여부
        mWebSettings.builtInZoomControls= false // 화면 확대 축소 허용 여부
        mWebSettings.cacheMode= WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        mWebSettings.domStorageEnabled= true  // 로컬저장소 허용 여부
    }
}