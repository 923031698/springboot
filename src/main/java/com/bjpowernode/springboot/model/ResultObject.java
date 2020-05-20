package com.bjpowernode.springboot.model;

public class ResultObject {

    private int statusCode;

    private String statusMessage;

    private Object data;

    public ResultObject(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResultObject(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public ResultObject(int statusCode, String statusMessage, Object data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}