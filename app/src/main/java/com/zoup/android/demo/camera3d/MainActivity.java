package com.zoup.android.demo.camera3d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private FrameLayout animationLayout;
    private ImageView frontView;
    private ImageView backView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationLayout = findViewById(R.id.animation_layout);
        frontView = findViewById(R.id.front_image);
        frontView.setImageResource(R.drawable.animals_13);
        backView = findViewById(R.id.back_image);

    }

    public void start(View view) {
        Camera3dAnimation camera3dAnimation = new Camera3dAnimation(backView, frontView);
        if (backView.getVisibility() == View.GONE) {
            camera3dAnimation.reverse();
        }
        animationLayout.startAnimation(camera3dAnimation);
    }

}
