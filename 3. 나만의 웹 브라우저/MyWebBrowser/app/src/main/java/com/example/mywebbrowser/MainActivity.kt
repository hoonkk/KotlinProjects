package com.example.mywebbrowser

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 웹뷰 기본 설정
        webView.apply {
            settings.javaScriptEnabled = true // 자바스크립트 동작
            webViewClient = WebViewClient()
        }

        webView.loadUrl("http://www.google.com") // 웹 뷰에 페이지 로딩

        urlEditText.setOnEditorActionListener { _, actionId, _ ->   // EditText에 글자 입력마다 호출
            if(actionId == EditorInfo.IME_ACTION_SEARCH) // 검색버튼이 눌렸는지 확인
            {
                webView.loadUrl(urlEditText.text.toString())
                true
            }
            else { false }
        }
    }

    override fun onBackPressed() { // 뒤로가기 동작 재정의
        if(webView.canGoBack()) {
            webView.goBack()
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}
