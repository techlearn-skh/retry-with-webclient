package com.skh.models;

public class ResultDetail {
    private String resource;
    private int status;
    private String message;

    public ResultDetail(String resource, int status, String message) {
        this.resource = resource;
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "ResultDetail{" +
                "resource='" + resource + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}