package com.nuance.covidgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CovidgramApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		log.debug("main: Entering method.");
		SpringApplication.run(CovidgramApplication.class, args);
		log.debug("main: Exiting method.");
	}
}

