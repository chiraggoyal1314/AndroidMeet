package org.mediasoup.droid.retrofitServices;

import org.mediasoup.droid.model.meetingModels.ChatModel;
import org.mediasoup.droid.model.meetingModels.ResponseModel;
import org.mediasoup.droid.model.meetingModels.CreateMeetingModel;
import org.mediasoup.droid.model.meetingModels.MeetingStatsModel;
import org.mediasoup.droid.model.meetingModels.ValidateParticipantModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MeetingApi {

    @POST("/createMeeting")
    Call<CreateMeetingModel> createMeeting(@Body org.mediasoup.droid.model.meetingModels.Body body);

    @POST("/addParticipantsInMeeting")
    Call<ResponseModel> addParticipantsInMeeting(@Body org.mediasoup.droid.model.meetingModels.Body body);

    @POST("/validateMeeting")
    Call<Object> validateMeeting(@Body org.mediasoup.droid.model.meetingModels.Body body);

    @POST("/validateParticipant")
    Call<ValidateParticipantModel> validateParticipant(@Body org.mediasoup.droid.model.meetingModels.Body body);

    @GET("/getMeetingChat")
    Call<Object> getMeetingChat(@Query("meetingId")String meetId);

    @POST("/getMeetingStats")
    Call<MeetingStatsModel> getMeetingStats(@Body org.mediasoup.droid.model.meetingModels.Body body);

    @POST("/saveMeetingChat")
    Call<ResponseModel> saveMeetingChat(@Body org.mediasoup.droid.model.meetingModels.Body body);

    @POST("/endMeeting")
    Call<ResponseModel> endMeeting(@Body org.mediasoup.droid.model.meetingModels.Body body);




    @GET("/getData")
    Call<String> getData();
}