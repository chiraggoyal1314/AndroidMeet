package org.mediasoup.droid.demo;

import static org.mediasoup.droid.demo.Application.body;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.mediasoup.droid.ViewModels.SplashViewModel;
import org.mediasoup.droid.ViewModels.SplashViewModelFactory;
import org.mediasoup.droid.demo.vm.LoginActivity;
import org.mediasoup.droid.model.meetingModels.Body;
import org.mediasoup.droid.model.meetingModels.ValidateMeetingModel;
import org.mediasoup.droid.utils.MobileUtils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SplashScreenActivity extends AppCompatActivity {

  SplashViewModel splashViewModel;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    Intent intent = getIntent();
    String action = intent.getAction();
    Uri data = intent.getData();
    if(data != null){
      Map<String,String> params = null;
      try {
        params = MobileUtils.splitQuery(new URL(data.toString()));
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }
      splashViewModel = new ViewModelProvider(this,new SplashViewModelFactory(getApplicationContext())).get(SplashViewModel.class);
      body.setMeetingId(params.containsKey("roomId")?params.get("roomId"):"");
      splashViewModel.validateMeeting(body);
      splashViewModel.getValidateMeetingModelMLD().observe(this, new Observer<ValidateMeetingModel>() {
        @Override
        public void onChanged(ValidateMeetingModel validateMeetingModel) {
          if(validateMeetingModel.getFlag() == 0){
            startActivity(new Intent(SplashScreenActivity.this, RoomActivity.class));
          } else {
            Toast.makeText(SplashScreenActivity.this, "Meeting does not Exist.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
          }
        }
      });
    } else {
      findViewById(R.id.mediasoup)
              .postDelayed(() -> startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class)), 1000);
    }
  }
}
