package com;


import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class UstHelloAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(UstHelloAppApplication.class, args);
		String[] beanNames = context.getBeanDefinitionNames();
		
		Arrays.sort(beanNames);
		for(String name: beanNames) {
			System.out.println(name);
		}
	}

	@RequestMapping("/")
	public String sayWelcome(){
		return "Welcome...";
	}
	
	@RequestMapping("/ust")
	public String sayWelcomeUST(){
		return "Welcome to UST global...";
		
	}
	
	@RequestMapping("/ust/admin")
	public String sayWelcomeAdmin(){
		return "Welcome admin! Hello!";
	}
	
	
}
