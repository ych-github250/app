package com.ych.core.web.entity;

import java.io.Serializable;
import java.util.HashMap;

public class Response extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String SUCCESS = "success";

    private static final String CODE = "code";

    private static final String MESSAGE = "message";

    private static final String DATA = "data";

    public Response(Boolean success) {
        super.put(SUCCESS, success);
    }

    public static Response success() {
        return new Response(true).code(ResponseCode.SUCCESS.getCode()).message(ResponseCode.SUCCESS.getMessage());
    }

    public static Response success(Object data) {
        return Response.success().data(data);
    }

    public static Response fail(ResponseCode responseCode) {
        return new Response(false).code(responseCode.getCode()).message(responseCode.getMessage());
    }

    public static Response fail() {
        return Response.fail(ResponseCode.FAIL);
    }

    public static Response fail(String message) {
        return Response.fail().message(message);
    }

    public Response code(Integer code) {
        this.put(CODE, code);
        return this;
    }

    public Response message(String message) {
        this.put(MESSAGE, message);
        return this;
    }

    public Response data(Object data) {
        this.put(DATA, data);
        return this;
    }

    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
