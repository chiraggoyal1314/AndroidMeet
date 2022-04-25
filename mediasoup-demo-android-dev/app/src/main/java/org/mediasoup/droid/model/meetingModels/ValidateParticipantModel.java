package org.mediasoup.droid.model.meetingModels;

public class ValidateParticipantModel {
    private String participantName;
    private String message;
    private float flag;


    // Getter Methods

    public String getParticipantName() {
        return participantName;
    }

    public String getMessage() {
        return message;
    }

    public float getFlag() {
        return flag;
    }

    // Setter Methods

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFlag(float flag) {
        this.flag = flag;
    }
}
