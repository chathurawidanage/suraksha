package com.romankaarayo.resources;

/**
 * @author Chathura Widanage
 */
public class CustomMessageResource {
    private String msg;
    private Object data;

    public CustomMessageResource(String message, String... args) {
        try {
            this.msg = processMsg(message, args);
        } catch (Exception ex) {
            this.msg = message;
        }
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private String processMsg(String message, String... args) throws Exception {
        //todo need to optimize this
        int index = 0;
        while (message.contains("{}")) {
            message = message.replaceFirst("\\{\\}", args[index++]);
        }
        return message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
