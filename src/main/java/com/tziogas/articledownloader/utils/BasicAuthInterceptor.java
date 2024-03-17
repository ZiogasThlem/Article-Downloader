package com.tziogas.articledownloader.utils;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public BasicAuthInterceptor(String username, String password) {
        this.credentials = Credentials.basic(username, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request authenticatedRequest = originalRequest.newBuilder()
                .header("Authorization", credentials)
                .build();
        return chain.proceed(authenticatedRequest);
    }
}