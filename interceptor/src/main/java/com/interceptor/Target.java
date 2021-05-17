package com.interceptor;

import com.interceptor.model.Request;
import com.interceptor.model.Response;

public interface Target {
    Response execute(Request request);
}
