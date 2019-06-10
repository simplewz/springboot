package org.simple.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value= {"classpath:person.properties"})
@Component
@ConfigurationProperties(prefix="person")
public class Person {
	//@Value("simple")
	private String name;
	//@Value("#{11*3}")
	private Integer age;
	//@Value("1995/3/11")
	private Date birthDay;
	//@Value("true")
	private Boolean boss;
	
	private Map<String, Object> occupation;
	private List<String> friends;
	
	private Cat cat;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Boolean getBoss() {
		return boss;
	}

	public void setBoss(Boolean boss) {
		this.boss = boss;
	}

	public Map<String, Object> getOccupation() {
		return occupation;
	}

	public void setOccupation(Map<String, Object> occupation) {
		this.occupation = occupation;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}

	public Cat getCat() {
		return cat;
	}

	public void setCat(Cat cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", birthDay=" + birthDay + ", boss=" + boss + ", occupation="
				+ occupation + ", friends=" + friends + ", cat=" + cat + "]";
	}
	
}
