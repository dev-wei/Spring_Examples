package com.mwei.spring;

import com.google.common.collect.Iterators;
import com.mwei.spring.gs_consuming_rest.Page;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void gs_rest_service(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static void gs_consuming_rest(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Page page = restTemplate.getForObject("http://graph.facebook.com/gopivotal", Page.class);
        System.out.println("Name:    " + page.getName());
        System.out.println("About:   " + page.getAbout());
        System.out.println("Phone:   " + page.getPhone());
        System.out.println("Website: " + page.getWebsite());
    }

    public static void main(String args[]) throws IOException {
        if (args != null && args.length == 1) {
            String arg = args[0];
            Iterable<String> it = Arrays.asList(args);
            Iterators.advance(it.iterator(), 1);

            String[] trimmedArgs = Iterators.toArray(it.iterator(), String.class);
            if (arg.equals("gs_rest_service")) {
                gs_rest_service(trimmedArgs);
            } else if (arg.equals("gs_consuming_rest")) {
                gs_consuming_rest(trimmedArgs);
            }
        }

        System.out.println("Parameter is wrong.");
    }
}
