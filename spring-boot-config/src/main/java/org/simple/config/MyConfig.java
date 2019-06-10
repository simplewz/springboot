package org.simple.config;

import org.simple.service.SayHelloService;
import org.simple.service.impl.SayHelloServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
	
	@Bean
	public SayHelloService sayHelloService() {
		return new SayHelloServiceImpl();
	}
	
}
