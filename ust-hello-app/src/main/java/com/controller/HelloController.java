package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.CredentialsConfiguration;

@RestController
public class HelloController {

	@Value("${welcome.message}")
	String message;
	
	
	@Autowired
	CredentialsConfiguration credentialsConfiguration;
	
	@RequestMapping("/hello")
	public String hello() {
		return message + credentialsConfiguration.getFullname()+"_UID:"+ String.valueOf(credentialsConfiguration.getUid());
	}
}
