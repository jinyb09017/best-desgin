package com.interceptor.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.interceptor.Interceptor;
import com.interceptor.Target;
import com.interceptor.model.Request;
import com.interceptor.model.Response;

/**
 * @author jinyb
 */
public class InterceptInvocation {
    private List<Interceptor> interceptors = new ArrayList<>();

    private Target target;

    private Response response;

    private Iterator<Interceptor> interceptorIterator = interceptors.iterator();

    public void addInterceptor(Interceptor interceptor) {
        if (!interceptors.contains(interceptor)) {
            interceptors.add(interceptor);
        }
        interceptorIterator = interceptors.iterator();
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Response invoke() {

        if (interceptorIterator.hasNext()) {
            Interceptor interceptor = interceptorIterator.next();
            interceptor.intercept(this);
        }
        if (response != null) {
            return response;
        }
        response = target.execute(new Request());
        return response;
    }
}
