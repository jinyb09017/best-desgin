package com.interceptor;

import com.interceptor.impl.InterceptInvocation;
import com.interceptor.model.Response;

/**
 * @author jinyb
 */
public interface Interceptor {
    Response intercept(InterceptInvocation interceptInvocation);
}
