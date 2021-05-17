package com.interceptor.impl;

import com.interceptor.Interceptor;
import com.interceptor.model.Response;

/**
 * @author ：xingze
 * @date ：Created in 2021-05-12 17:16
 * @description：日志拦截实现
 * @modified By：
 * @version: 1.0.0
 */
public class AuditInterceptor implements Interceptor {
    @Override
    public Response intercept(InterceptInvocation interceptInvocation) {
        System.out.println("audit start");
        Response response = interceptInvocation.invoke();
        System.out.println("audit end");
        return response;
    }
}