package org.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.simple.bean.Person;
import org.simple.service.SayHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigApplicationTests {
	@Autowired
	Person person;
	@Autowired
	ApplicationContext ioc;
	@Autowired
	SayHelloService sayHelloService;
	
	@Test
	public void testSayHelloService() {
		boolean b=ioc.containsBean("sayHelloService");
		System.out.println(b);
		//System.out.println(sayHelloService.sayHello("Simple"));
	}
	
	@Test
	public void test() {
		System.out.println(person);
	}
}
