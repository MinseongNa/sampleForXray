package com.example.xray.config;

import javax.servlet.Filter;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;
import com.amazonaws.xray.strategy.sampling.LocalizedSamplingStrategy;
import com.example.xray.constant.OrderFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class AwsXrayConfiguration {
    static {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard();

        builder.withSamplingStrategy(
                new LocalizedSamplingStrategy(AwsXrayConfiguration.class.getResource("/sampling-rules.json")));

        AWSXRay.setGlobalRecorder(builder.build());
    }

    @Bean
    @Order(OrderFilter.AWS_XRAY_SERVLET_FILTER)
    public Filter TracingFilter() {
        return new AWSXRayServletFilter("sample_xray");
    }
}
