package maven;

import org.joda.time.LocalTime;
public class HelloWorld{
	public static void main(String[] args){
		Greeter greeter=new Greeter();
		LocalTime currentTime=new LocalTime();
		System.out.println("The current loal time is:"+currentTime);
		System.out.println(greeter.sayHello());
	}
}
