package com.example.smartcard;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    TextView bottomline;
    ImageView logoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_activity_screen);

        bottomline = findViewById(R.id.bottomtext);
        logoImg = findViewById(R.id.logo_img);

        bottomline.animate().alpha(1f).setDuration(2000);
        logoImg.animate().alpha(1f).setDuration(2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}

