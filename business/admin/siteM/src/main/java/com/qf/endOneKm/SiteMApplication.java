package com.qf.endOneKm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
public class SiteMApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteMApplication.class,args);

    }
}
