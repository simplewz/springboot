package org.simple.service.impl;

import org.simple.service.SayHelloService;

public class SayHelloServiceImpl implements SayHelloService {

	@Override
	public String sayHello(String name) {
		return "Hello"+name;
	}
}
