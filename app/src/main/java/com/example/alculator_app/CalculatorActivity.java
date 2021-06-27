package com.example.alculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    private CalculatorModel calculator;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme());
        setContentView(R.layout.activity_main);
        initChanger();

        int[] numberIds = new int[]{
                R.id.zero,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine,
                R.id.point
        };

        int[] actionsIds = new int[]{
                R.id.plus,
                R.id.minus,
                R.id.multiply,
                R.id.division,
                R.id.equals
        };

        text = findViewById(R.id.text);
        calculator = new CalculatorModel();


        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.onNumPressed(view.getId());
                text.setText(calculator.getText());
            }
        };

        View.OnClickListener actionButtonOnclickListener = view -> {
            calculator.onActionPressed(view.getId());
            text.setText(calculator.getText());
        };

        for (int numberId : numberIds) {
            findViewById(numberId).setOnClickListener(numberButtonClickListener);
        }

        for (int actionsId : actionsIds) {
            findViewById(actionsId).setOnClickListener(actionButtonOnclickListener);
        }

        findViewById(R.id.reset).setOnClickListener(view -> {
            calculator.reset();
            text.setText(calculator.getText());
        });

    }

    private int day_t = 1;
    private String key = "key";
    private String app_theme = "theme";


    private void initChanger() {
        initButton(findViewById(R.id.day), day_t);
    }

    private void initButton(Button button, int codeStyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme(codeStyle);
                recreate();
            }
        });
    }

    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPreferences = getSharedPreferences(key, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(app_theme, codeStyle);
        editor.apply();
    }

    private int getAppTheme() {
        int codeStyle = day_t;
        SharedPreferences sharedPreferences = getSharedPreferences(key, MODE_PRIVATE);
        return sharedPreferences.getInt(app_theme, codeStyle);
    }
}
