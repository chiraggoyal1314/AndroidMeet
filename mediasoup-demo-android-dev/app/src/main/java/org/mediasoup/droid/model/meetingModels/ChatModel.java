package org.mediasoup.droid.model.meetingModels;

import java.util.List;

public class ChatModel {
    private String message;
    private float flag;
    List<SingleChatModel> chatList;

    public ChatModel(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getFlag() {
        return flag;
    }

    public void setFlag(float flag) {
        this.flag = flag;
    }

    public List<SingleChatModel> getChatList() {
        return chatList;
    }

    public void setChatList(List<SingleChatModel> chatList) {
        this.chatList = chatList;
    }
}