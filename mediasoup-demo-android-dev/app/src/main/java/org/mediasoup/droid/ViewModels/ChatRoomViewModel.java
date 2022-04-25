package org.mediasoup.droid.ViewModels;

import static org.mediasoup.droid.demo.Application.body;
import static org.mediasoup.droid.demo.ChatRoom.progressBar;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.mediasoup.droid.demo.Application;
import org.mediasoup.droid.demo.ChatRoom;
import org.mediasoup.droid.model.meetingModels.Body;
import org.mediasoup.droid.model.meetingModels.ChatModel;
import org.mediasoup.droid.model.meetingModels.SingleChatModel;
import org.mediasoup.droid.model.meetingModels.ValidateMeetingModel;
import org.mediasoup.droid.retrofitServices.MeetingApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatRoomViewModel extends ViewModel {

    private Context context;
    private MutableLiveData<ChatModel> chatModelMLD;

    public ChatRoomViewModel(Context context){
        this.context = context;
        chatModelMLD = new MutableLiveData<>();
    }

    public void getAllChats(String meetId) {
        MeetingApi meetingApi = Application.retrofit.create(MeetingApi.class);
        Call<Object> call = (Call<Object>) meetingApi.getMeetingChat(meetId);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call,Response<Object> response) {
//                progressBar.setVisibility(View.GONE);
                Log.d("status",""+ response.code());
                if(response.isSuccessful()){
                    Log.d("msg","You successfully ran this code");
                    chatModelMLD.postValue(new ObjectMapper().convertValue(response.body(),ChatModel.class));
                }
            }
            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.d("msg","Message is " + t);
            }
        });
    }
    public LiveData<ChatModel> getChatModelMLD() {
        return chatModelMLD;
    }
}
