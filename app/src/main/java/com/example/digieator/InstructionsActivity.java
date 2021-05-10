package com.example.digieator;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InstructionsActivity extends AppCompatActivity {

    WebView webView;

    public String fileName = "myIns.html";
    public BottomNavigationView buttom_nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_instructions);
        // init webView
        webView = (WebView) findViewById(R.id.simpleWebView);
        // displaying content in WebView from html file that stored in assets folder
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/" + fileName);

        initButtomNavigationMenu();
    }


    private void initButtomNavigationMenu() {
        buttom_nav_view = (BottomNavigationView) findViewById(R.id.buttom_nav_view);
        buttom_nav_view.setItemIconTintList(null);
        buttom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.btm_nav_mem:
                        Intent myIntent2 = new Intent(InstructionsActivity.this,
                                com.example.digieator.HistoryActivity.class);
                        startActivity(myIntent2);
                        break;

                    case R.id.btm_nav_calculator:
                        Intent myIntent = new Intent(com.example.digieator.InstructionsActivity.this,
                                com.example.digieator.MainActivity.class);
                        startActivity(myIntent);
                        break;

                    case R.id.btm_nav_colors:
                        Intent myIntent1 = new Intent(com.example.digieator.InstructionsActivity.this,
                                com.example.digieator.ThemeActivity.class);
                        startActivity(myIntent1);
                        break;

                    case R.id.btm_nav_ins:

                        break;
                    case R.id.btm_nav_ref:
                        Intent myIntent3 = new Intent(com.example.digieator.InstructionsActivity.this,
                                com.example.digieator.ReferencesActivity.class);
                        startActivity(myIntent3);
                        break;

                }
                return true;
            }
        });

    }


}//end of Instruction Activity class
