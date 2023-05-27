package com.blackjack.utils;

import com.blackjack.errors.ErrorResponse;

public class Response<T> {
    private T data;

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
