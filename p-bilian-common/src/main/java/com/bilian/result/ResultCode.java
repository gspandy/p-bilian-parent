package com.bilian.result;


public enum ResultCode {
    /**
     * Success
     */
    C200(200, "Success"),
    /**
     * Forbidden
     */
    C403(403, "Forbidden"),
    /**
     * Not Found"
     */
    C404(404, "Not Found"),
    /**
     * Parameters Error"
     */
    C406(406, "Parameters Error"),
    /**
     * Internal Server Error"
     */
    C500(500, "Internal Server Error");

    private int code;//code
    private String desc;//description

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCode format(String name) {
        for (ResultCode value : ResultCode.values()) {
            if (name.equals(value.toString())) {
                return value;
            }
        }
        return null;

    }

    public static ResultCode formatName(int valKey) {
        for (ResultCode value : ResultCode.values()) {
            if (valKey == value.getCode()) {
                return value;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
