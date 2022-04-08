package org.mediasoup.droid.demo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import org.mediasoup.droid.demo.vm.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    findViewById(R.id.mediasoup)
        .postDelayed(() -> startActivity(new Intent(this, LoginActivity.class)), 1000);
  }
}
