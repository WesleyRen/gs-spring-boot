package com.example.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CustomKeyGenerator implements KeyGenerator {
 
    public Object generate(Object target, Method method, Object... params) {
        String paramsStr = StringUtils.arrayToDelimitedString(params, "_");
        String paramsStrEncoded = URLEncoder.encode(paramsStr, StandardCharsets.UTF_8);
        return target.getClass().getSimpleName() + "_"
          + method.getName() + "_"
          + paramsStrEncoded;
    }
}
