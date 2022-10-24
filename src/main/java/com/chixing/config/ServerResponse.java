package com.chixing.config;

import lombok.Data;

@Data
public class ServerResponse {
    private int resultCode;
    private String reason;
    private Object data;

    public ServerResponse() {
    }

    public ServerResponse(int resultCode, String reason, Object data) {
        this.resultCode = resultCode;
        this.reason = reason;
        this.data = data;
    }
    public static ServerResponse success(String reason,Object data){
        return new ServerResponse(200,reason,data);
    }

    public static ServerResponse fail(String reason,Object data){
        return new ServerResponse(201,reason,data);
    }
}
