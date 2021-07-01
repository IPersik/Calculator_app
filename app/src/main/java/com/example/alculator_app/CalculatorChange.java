package com.example.alculator_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorChange extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(convertCode(getAppTheme()));

        setContentView(R.layout.change_topic);
        initChanger();

        (findViewById(R.id.day)).setOnClickListener(v -> {
            this.startActivity((new Intent(this, CalculatorActivity.class)));
        });

        (findViewById(R.id.night)).setOnClickListener(v -> {
            this.startActivity((new Intent(this, CalculatorActivity.class)));
        });
    }

    private int convertCode(int code) {
        switch (code){
            case Light:
                return R.style.light;
            default: Dark:
                return R.style.dark;
        }
    }

    private final int  Light = 0;
    private final int  Dark = 1;
    private final String KEY_PREF = "key";
    private final String APP_THEME = "theme";

    private void initChanger(){
        initButton(findViewById(R.id.day), Light);
        initButton(findViewById(R.id.night), Dark);
    }

    private void initButton(RadioButton button, int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme(codeStyle);
                recreate();
            }
        });
    }

    private void setAppTheme(int codeStyle){
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }

    private int getAppTheme() {
        int codeStyle = Light;
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_PREF, MODE_PRIVATE);
        return sharedPreferences.getInt(APP_THEME, codeStyle);
    }

}