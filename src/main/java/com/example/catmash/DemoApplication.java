package com.example.catmash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@RequestMapping("/")
    	@ResponseBody
   	String home() {
      		return "Hello World!";
    	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}