package org.mediasoup.droid.ViewModels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.mediasoup.droid.demo.Application;
import org.mediasoup.droid.model.meetingModels.Body;
import org.mediasoup.droid.model.meetingModels.ValidateMeetingModel;
import org.mediasoup.droid.retrofitServices.MeetingApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends ViewModel {

    private Context context;
    private MutableLiveData<ValidateMeetingModel> validateMeetingModelMLD;

    public LoginViewModel(Context context){
        this.context = context;
        validateMeetingModelMLD = new MutableLiveData<>();
    }

    public void validateMeeting(Body body){
        MeetingApi meetingApi = Application.retrofit.create(MeetingApi.class);
        Call<Object> call = (Call<Object>) meetingApi.validateMeeting(body);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Log.d("status",""+ response.code());
                if(response.isSuccessful()){
                    Log.d("msg","You successfully ran this code");
                    validateMeetingModelMLD.postValue(new ObjectMapper().convertValue(response.body(), ValidateMeetingModel.class));
                }
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("msg","Message is " + t);
            }
        });
    }
    public LiveData<ValidateMeetingModel> getValidateMeetingModelMLD() {
        return validateMeetingModelMLD;
    }
}
