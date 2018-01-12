package com.bilian.result;

import java.io.Serializable;


public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = -4696898674758059398L;


    private int code;

    private String message;

    private T data;

    private boolean ok = true;

    public ResultResponse(int code, String message, T data) {
        this.code = code;
        this.ok = code == 200;
        this.message = message;
        if (null != data) {
            this.data = data;
        }
    }


    public ResultResponse(int code, T data) {
        this.code = code;
        this.ok = code == 200;
        this.data = data;
    }

    public ResultResponse(T data) {

        this.data = data;
    }

    public ResultResponse() {

    }

    public ResultResponse<T> success(T data) {

        return new ResultResponse<>(ResultCode.C200.getCode(), data);
    }

    public ResultResponse success(T data, String message) {
        return new ResultResponse<>(ResultCode.C200.getCode(), message, data);
    }

    public ResultResponse<T> ok(String message) {
        return new ResultResponse<>(ResultCode.C200.getCode(), message, null);
    }

    public ResultResponse<T> no(String message) {
        return new ResultResponse<>(ResultCode.C500.getCode(), message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        this.ok = code == 200;
    }

    public boolean getOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
