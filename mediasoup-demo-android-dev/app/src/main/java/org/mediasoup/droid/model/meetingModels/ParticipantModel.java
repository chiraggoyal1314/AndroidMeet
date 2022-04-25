package org.mediasoup.droid.model.meetingModels;

import java.util.ArrayList;

public class ParticipantModel {
    private String participantId;
    private String participantName;
    private float durationInSeconds;
    ArrayList <DurationModel> participantChildList = new ArrayList<DurationModel>();


    // Getter Methods

    public String getParticipantId() {
        return participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public float getDurationInSeconds() {
        return durationInSeconds;
    }

    // Setter Methods

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public void setDurationInSeconds(float durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}