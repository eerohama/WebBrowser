package com.example.webbrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private WebView web;
    private EditText editUrl;
    private BrowserHistory BH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = (WebView) findViewById(R.id.webView);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        editUrl = (EditText) findViewById(R.id.editUrl);
        BH = BrowserHistory.getInstance();

        editUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUrl.setText("");
            }
        });
    }

    public void searchUrl(View view){
        String str = editUrl.getText().toString();
        String url;
        if(str.contains(".html")){
            url = "file:///android_asset/" + str;
        } else {
            url = "http://" + str;
        }
        web.loadUrl(url);
        BH.addAdress(url);
    }

    public void refreshUrl(View view){
        String url = BH.getCurrent();
        web.loadUrl(url);
    }

    public void previousUrl(View view){
        String previous = BH.getPrevious();
        editUrl.setText(previous);
        web.loadUrl(previous);
    }

    public void nextUrl(View view){
        String next = BH.getNext();
        editUrl.setText(next);
        web.loadUrl(next);
    }

    public void executeJavascript(View view){
        web.evaluateJavascript("javascript:shoutOut()", null);
    }

    public void intitializeJavascript(View view){
        web.evaluateJavascript("javascript:initialize()", null);
    }

    public void print(View view){
        for(String s : BH.getList()){
            System.out.println(s);
        }
    }
}