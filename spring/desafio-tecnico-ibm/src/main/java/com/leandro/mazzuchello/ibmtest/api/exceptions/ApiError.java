package com.leandro.mazzuchello.ibmtest.api.exceptions;

import java.util.List;

public class ApiError {
    private List<String> messages;

    public ApiError(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessage(List<String> messages) {
        this.messages = messages;
    }
}
