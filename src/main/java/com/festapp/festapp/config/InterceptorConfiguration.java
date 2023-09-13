package com.festapp.festapp.config;

import com.festapp.festapp.interceptors.GeneralInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    private GeneralInterceptor generalInterceptor;

    @Autowired
    public InterceptorConfiguration(GeneralInterceptor generalInterceptor) {
        this.generalInterceptor = generalInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(generalInterceptor);
    }
}
