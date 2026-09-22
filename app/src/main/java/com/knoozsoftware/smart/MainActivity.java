package com.knoozsoftware.smart;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;
    private static final String TARGET_URL = "https://smart.knoozsoftware.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myWebView = new WebView(this);
        setContentView(myWebView);

        WebSettings webSettings = myWebView.getSettings();
        
        // تفعيل الجافا سكريبت وتخزين DOM
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        
        // --- إلغاء التخزين المؤقت (الكاش) نهائياً لحل مشكلة الصفحات القديمة ---
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        myWebView.clearCache(true);
        myWebView.clearHistory();

        // جعل الرابط يفتح داخل التطبيق بدلاً من المتصفح الخارجي
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // تحميل موقع الإدارة المدرسية الذكية
        myWebView.loadUrl(TARGET_URL);
    }

    // معالجة زر الرجوع في الهاتف للعودة للصفحة السابقة في الموقع
    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
