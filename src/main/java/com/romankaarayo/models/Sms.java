package com.romankaarayo.models;

/**
 * @author Chathura Widanage
 */
public class Sms {
    private String message;
    private String address;
    private String requestId;
    private String encoding;
    private String version;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "message='" + message + '\'' +
                ", address='" + address + '\'' +
                ", requestId='" + requestId + '\'' +
                ", encoding=" + encoding +
                ", version='" + version + '\'' +
                '}';
    }
}
