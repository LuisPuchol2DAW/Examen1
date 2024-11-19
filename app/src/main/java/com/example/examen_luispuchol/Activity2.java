package com.example.examen_luispuchol;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText editText;
    private SeekBar seekBarTextSize, seekBarRed, seekBarGreen, seekBarBlue;
    private Button buttonApplyTextAndSize, buttonApplyColor;
    private TextView textViewFragment2;
    private ImageView icon1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Activity has been created");
        super.onCreate(savedInstanceState);
        startStuff();
        startLayout();

        startListeners();
    }

    public void startListeners() {


        changeActivityListener();

        textAndSizeListener();

        colorsListener();
    }

    private void colorsListener() {
        buttonApplyColor.setOnClickListener(v -> {
            //seekbars go 0-100, and RGB 0-255
            int red = (int) (seekBarRed.getProgress() * 2.55);
            int green = (int) (seekBarGreen.getProgress() * 2.55);
            int blue = (int) (seekBarBlue.getProgress() * 2.55);
            textViewFragment2.setTextColor(android.graphics.Color.rgb(red, green, blue));
        });
    }

    private void textAndSizeListener() {
        buttonApplyTextAndSize.setOnClickListener(v -> {
            int textSize = seekBarTextSize.getProgress() + 12; //min text size its 12
            textViewFragment2.setTextSize(textSize);
            textViewFragment2.setText(editText.getText().toString());
        });
    }

    private void changeActivityListener() {
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2.this, MainActivity.class );
                startActivity(intent);
            }
        });
    }

    public void startStuff() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void startLayout() {
        icon1 = findViewById(R.id.icon_1);
        editText = findViewById(R.id.editText);
        seekBarTextSize = findViewById(R.id.seekBarTextSize);
        seekBarRed = findViewById(R.id.seekBarRed);
        seekBarGreen = findViewById(R.id.seekBarGreen);
        seekBarBlue = findViewById(R.id.seekBarBlue);
        buttonApplyTextAndSize = findViewById(R.id.buttonApplyTextAndSize);
        buttonApplyColor = findViewById(R.id.buttonApplyColor);
        textViewFragment2 = findViewById(R.id.textViewFragment2);
    }
}