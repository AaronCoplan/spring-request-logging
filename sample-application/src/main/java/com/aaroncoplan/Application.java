package com.aaroncoplan;

import com.aaroncoplan.springrequestlogging.RequestData;
import com.aaroncoplan.springrequestlogging.RequestLoggerFactory;
import com.aaroncoplan.springrequestlogging.SingleRequestLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
@RestController
public class Application implements WebMvcConfigurer {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // demonstrating a simple SingleRequestLogger
        registry.addInterceptor(RequestLoggerFactory.buildSingleRequestLogger(new SingleRequestLogger() {
            Logger l = LoggerFactory.getLogger("SRL");
            @Override
            public void processSingleRequest(RequestData requestData) {
                l.info(requestData.toString());
            }
        }));
    }

    @GetMapping("/")
    public String greeting() {
        return "Hello World";
    }

    @GetMapping("/compute")
    public double doSomeComputations() {
        ArrayList<Double> doubleArrayList = new ArrayList<>();
        for(int i = 0; i < 1000000; ++i) {
            doubleArrayList.add(Math.random());
        }
        Collections.sort(doubleArrayList);
        return doubleArrayList.get(0);
    }

    @GetMapping("/error")
    public double throwAnError() {
        throw new RuntimeException("Something went wrong!");
    }
}