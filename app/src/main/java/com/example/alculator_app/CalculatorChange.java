package com.example.alculator_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorChange extends AppCompatActivity {

    protected final int Light = 0;
    protected final int Dark = 1;
    private final String KEY_PREF = "key";
    private final String APP_THEME = "theme";
    protected int styleCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(codeStyleToStyleId(getAppTheme()));

        setContentView(R.layout.change_topic);

        initChanger();

        /*(findViewById(R.id.day)).setOnClickListener(v -> {
            Intent intentAnswer = new Intent();
            intentAnswer.putExtra("answer", 0);
            setResult(CalculatorActivity.requestCode);
            finish();
        });

        (findViewById(R.id.night)).setOnClickListener(v -> {
            Intent intentAnswer = new Intent();
            intentAnswer.putExtra("answer", 1);
            setResult(CalculatorActivity.requestCode);
            finish();
        }); */

        (findViewById(R.id.button_back)).setOnClickListener(v -> {
            Intent intentAnswer = new Intent();
            intentAnswer.putExtra("answer", 0);
            setResult(CalculatorActivity.requestCode);
            finish();
        });
    }


    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case Light:
                return R.style.light;
            case Dark:
                return R.style.dark;
        }
        return codeStyle;
    }


   private void initChanger() {
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

    protected void setAppTheme(int codeStyle) {
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.apply();
    }

    protected int getAppTheme() {
        int codeStyle = Light;
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_PREF, MODE_PRIVATE);
        return sharedPreferences.getInt(APP_THEME, codeStyle);
    }
}