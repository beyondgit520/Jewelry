package com.app.http;

/**
 * Created by limin on 2016/7/4.
 */
public class HttpResult<T> {

    public Integer code;
    /**
     * 实际的data
     */
    private T data;

    public T getData() {
        return data;
    }
}
