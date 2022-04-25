package org.mediasoup.droid.model.meetingModels;

import java.util.Date;

public class ValidateMeetingModel {
    private String meetingTitle;
    private String meetingDescription;
    private Date meetingStartTime;
    private Date meetingEndTime;
    private Date currentTime;
    private String presenterId;
    private String presenterName;
    private String meetingType;
    private String message;
    private float flag;


    // Getter Methods

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public String getMeetingDescription() {
        return meetingDescription;
    }

    public Date getMeetingStartTime() {
        return meetingStartTime;
    }

    public Date getMeetingEndTime() {
        return meetingEndTime;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public String getPresenterId() {
        return presenterId;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public String getMessage() {
        return message;
    }

    public float getFlag() {
        return flag;
    }

    // Setter Methods

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public void setMeetingDescription(String meetingDescription) {
        this.meetingDescription = meetingDescription;
    }

    public void setMeetingStartTime(Date meetingStartTime) {
        this.meetingStartTime = meetingStartTime;
    }

    public void setMeetingEndTime(Date meetingEndTime) {
        this.meetingEndTime = meetingEndTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public void setPresenterId(String presenterId) {
        this.presenterId = presenterId;
    }

    public void setPresenterName(String presenterName) {
        this.presenterName = presenterName;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFlag(float flag) {
        this.flag = flag;
    }
}
