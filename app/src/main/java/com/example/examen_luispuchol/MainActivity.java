package com.example.examen_luispuchol;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private View background, sunShape, bottomBar;
    private ImageView icon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Activity has been created");
        super.onCreate(savedInstanceState);
        startStuff();
        startLayout();

        startListeners();
    }

    private void startListeners() {
        animationListener();

        changeActivityListener();
    }

    private void changeActivityListener() {
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            }
        });
    }

    private void animationListener() {
        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float targetPosition = getFinalPosition();

                ObjectAnimator moveSun = getSunAnimation(targetPosition);

                ValueAnimator colorAnimation = getColorAnimation();

                moveSun.start();
                colorAnimation.start();
            }

            private @NonNull ValueAnimator getColorAnimation() {
                ValueAnimator colorAnimation = assignColours();
                colorAnimation.setDuration(4000);

                colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        background.setBackgroundColor((int) animator.getAnimatedValue());
                    }
                });
                return colorAnimation;
            }

            private ValueAnimator assignColours() {
                ValueAnimator colorAnimation = ValueAnimator.ofObject(
                        new ArgbEvaluator(),
                        getResources().getColor(R.color.sky_blue),
                        getResources().getColor(R.color.gray),
                        getResources().getColor(R.color.orange),
                        getResources().getColor(R.color.darkest_blue)
                );
                return colorAnimation;
            }

            private @NonNull ObjectAnimator getSunAnimation(float targetPosition) {
                ObjectAnimator moveSun = ObjectAnimator.ofFloat(sunShape, "translationY", targetPosition);
                moveSun.setDuration(6000);
                return moveSun;
            }

            private float getFinalPosition() {
                float bottomBarTop = bottomBar.getY();
                float sunShapeHeight = sunShape.getHeight();
                float targetPosition = bottomBarTop - sunShapeHeight;
                return targetPosition;
            }
        });
    }

    public void startStuff() {
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void startLayout() {
        background = findViewById(R.id.background);
        sunShape = findViewById(R.id.sun_shape);
        bottomBar = findViewById(R.id.bottom_bar);
        icon2 = findViewById(R.id.icon_2);
    }

}