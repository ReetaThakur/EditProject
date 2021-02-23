package com.xyz.multipleviewtypechat.model;

public class ReceiverChat extends BaseModel {

    private String message;
    private String time;

    public ReceiverChat(String message, String time) {
        this.message = message;
        this.time = time;
    }

    @Override
    public int getItemType() {
        return 2;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
