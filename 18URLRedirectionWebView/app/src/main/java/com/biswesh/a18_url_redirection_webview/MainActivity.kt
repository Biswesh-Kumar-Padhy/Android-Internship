package com.biswesh.a18_url_redirection_webview

import android.os.Bundle
import com.biswesh.a18_url_redirection_webview.ui.theme._18URLRedirectionWebViewTheme
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

//    private lateinit var webView: WebView //from activity_main.xml
//    @SuppressLint("SetJavaScriptEnabled") //to load JS for browser
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        try {
//            webView = findViewById(R.id.webView)
//
//            // Enable JavaScript
//            webView.settings.javaScriptEnabled = true
//            // Set WebViewClient to handle URL redirection
//            webView.webViewClient = MyWebViewClient()
//            // Load YouTube URL
//            webView.loadUrl("https://www.youtube.com")
//        } catch (e: Exception) { Log.e("MainActivity", "Error initializing WebView", e) }
//    }
//    private class MyWebViewClient : WebViewClient() {
//        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//            request?.url?.let {
//                view?.loadUrl(it.toString())
//                return true
//            }
//            return super.shouldOverrideUrlLoading(view, request)
//        }
//    }

    @HighlightClick(url = "https://www.netflix.com/")
    private lateinit var highlightedText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        highlightedText = findViewById(R.id.highlighted_text)
        setHighlightedText(highlightedText, "Click Here")
    }

    private fun setHighlightedText(textView: TextView, text: String) {
        val spannableString = SpannableString(text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val url = this@MainActivity::class.java
                    .getDeclaredField("highlightedText")
                    .getAnnotation(HighlightClick::class.java)?.url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
        spannableString.setSpan(clickableSpan, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

}


//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            _18URLRedirectionWebViewTheme {
//
//            }
//        }
//    }
//}