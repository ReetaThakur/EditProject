package com.xyz.multipleviewtypechat.model;

public class SenderChat extends BaseModel {

    private String message;

    @Override
    public int getItemType() {
        return 1;
    }

    public SenderChat(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
