package org.mediasoup.droid.model.meetingModels;

public class ResponseModel {
    private String message;
    private float flag;


    // Getter Methods

    public String getMessage() {
        return message;
    }

    public float getFlag() {
        return flag;
    }

    // Setter Methods

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFlag(float flag) {
        this.flag = flag;
    }
}