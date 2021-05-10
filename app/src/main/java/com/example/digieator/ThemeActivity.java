package com.example.digieator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ThemeActivity extends AppCompatActivity {
    
    SharedPreferences sp;
    Switch aSwitch;
    Button button;
    TextView textView;
    public BottomNavigationView buttom_nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Check preference to get status of theme
        sp = getSharedPreferences("darktheme.apollo.darktheme.Theme", MODE_PRIVATE);
        Boolean state = sp.getBoolean("switchKey", false);
        String themeValue = sp.getString("Theme", "Default");
        try {
            switch (themeValue) {
                case "Dark":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    break;
                case "Light":
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error changing theme", Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_theme);
        initButtomNavigationMenu();
        aSwitch = findViewById(R.id.switch1);


        if (state) {
            aSwitch.setChecked(true);
        } else {
            aSwitch.setChecked(false);
        }


        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked()) {
                    SharedPreferences sharedPreferences = getSharedPreferences("darktheme.apollo.darktheme.Theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Theme", "Dark");
                    editor.putBoolean("switchKey", true);
                    editor.putString("name", "Dark Mode");
                    editor.apply();
                    recreate();

                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("darktheme.apollo.darktheme.Theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Theme", "Light");
                    editor.putString("name", "Light Mode");
                    editor.putBoolean("switchKey", false);
                    editor.apply();
                    recreate();
                }
            }
        });



        aSwitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getActionMasked() == MotionEvent.ACTION_MOVE;
            }
        });



    }



    private void initButtomNavigationMenu() {
        buttom_nav_view = (BottomNavigationView)findViewById(R.id.buttom_nav_view);
        buttom_nav_view.setItemIconTintList(null);
        buttom_nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.btm_nav_mem:

                        Intent myIntent1 = new Intent(com.example.digieator.ThemeActivity.this,
                              HistoryActivity.class);
                        startActivity(myIntent1);
                        break;

                    case R.id.btm_nav_calculator:

                        Intent myIntent = new Intent(com.example.digieator.ThemeActivity.this,
                                com.example.digieator.MainActivity.class);
                        startActivity(myIntent);
                        break;

                    case R.id.btm_nav_colors:

                        break;

                    case R.id.btm_nav_ins:
                        Intent myIntent2 = new Intent(com.example.digieator.ThemeActivity.this,
                                InstructionsActivity.class);
                        startActivity(myIntent2);
                        break;
                    case R.id.btm_nav_ref:
                        Intent myIntent3 = new Intent(com.example.digieator.ThemeActivity.this,
                                ReferencesActivity.class);
                        startActivity(myIntent3);
                        break;


                }
                return true;
            }
        });

    }




}