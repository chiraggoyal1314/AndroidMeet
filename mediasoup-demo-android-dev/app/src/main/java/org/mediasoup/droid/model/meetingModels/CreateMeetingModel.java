package org.mediasoup.droid.model.meetingModels;

public class CreateMeetingModel {
    private String participantUrl;
    private String presenterUrl;
    private String meetingId;
    private String message;
    private float flag;


    // Getter Methods

    public String getParticipantUrl() {
        return participantUrl;
    }

    public String getPresenterUrl() {
        return presenterUrl;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public String getMessage() {
        return message;
    }

    public float getFlag() {
        return flag;
    }

    // Setter Methods

    public void setParticipantUrl(String participantUrl) {
        this.participantUrl = participantUrl;
    }

    public void setPresenterUrl(String presenterUrl) {
        this.presenterUrl = presenterUrl;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFlag(float flag) {
        this.flag = flag;
    }
}