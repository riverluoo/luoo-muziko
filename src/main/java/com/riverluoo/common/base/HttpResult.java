package com.riverluoo.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class HttpResult<T> implements Serializable {

    // 状态码：1成功，其他为失败
    public int code;

    // 成功为success，其他为失败原因
    public String message;

    // 数据结果集
    public T data;

    public HttpResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <U> HttpResult success(U data, String message) {
        return new HttpResult<>(Stage.SUCCESS.getStageNum(), message, data);
    }

    public static <U> HttpResult success(U data) {
        return HttpResult.success(data, "success");
    }

    public static HttpResult success() {
        return HttpResult.success(null, "success");
    }

    public static HttpResult fail(String message) {
        return HttpResult.fail(message, Stage.FAIL.getStageNum());
    }

    public static HttpResult fail(List<String> messages) {
        String message = messages.stream().collect(Collectors.joining("，"));
        return HttpResult.fail(message);
    }

    public static HttpResult fail(String message, int code) {
        return new HttpResult<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    /**
     * 状态码
     */
    public enum Stage {
        SUCCESS(1),
        FAIL(0);
        private int stageNum;

        Stage(int stageNum) {
            this.stageNum = stageNum;
        }

        public int getStageNum() {
            return stageNum;
        }
    }
}
