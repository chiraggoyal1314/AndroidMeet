package org.mediasoup.droid.demo.vm;

import static org.mediasoup.droid.demo.Application.body;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;

import org.mediasoup.droid.ViewModels.LoginViewModel;
import org.mediasoup.droid.ViewModels.LoginViewModelFactory;
import org.mediasoup.droid.demo.R;
import org.mediasoup.droid.demo.RoomActivity;
import org.mediasoup.droid.demo.SplashScreenActivity;
import org.mediasoup.droid.demo.databinding.ActivityLoginBinding;
import org.mediasoup.droid.model.meetingModels.Body;
import org.mediasoup.droid.model.meetingModels.ValidateMeetingModel;
import org.mediasoup.droid.utils.MobileUtils;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;//
    private ActivityLoginBinding loginBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        }
        else{
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        loginViewModel = new ViewModelProvider(this,new LoginViewModelFactory(getApplicationContext())).get(LoginViewModel.class);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            loginBinding.themeMode.setChecked(true);
        }
        loginBinding.themeMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        loginBinding.joinMeeting.setOnClickListener(v->{
            validateMeeting();
        });
        loginViewModel.getValidateMeetingModelMLD().observe(LoginActivity.this, new Observer<ValidateMeetingModel>() {
            @Override
            public void onChanged(ValidateMeetingModel validateMeetingModel) {
                if(validateMeetingModel.getFlag() == 0){
                    startActivity(new Intent(LoginActivity.this, RoomActivity.class));
                } else {
                    MobileUtils.notifyUserShort(getApplicationContext(),"Meeting does not Exist.");
                }
            }
        });
    }

    private void validateMeeting(){
        if(!loginBinding.meetingCode.getText().toString().isEmpty()) {
            body = new Body();
            body.setMeetingId(loginBinding.meetingCode.getText().toString());
            body.setParticipantName(loginBinding.username.getText().toString());
            loginViewModel.validateMeeting(body);
        } else {
            MobileUtils.notifyUserShort(getApplicationContext(),"Please enter valid meeting id");
        }
    }

}