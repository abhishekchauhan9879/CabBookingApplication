package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

	String name;
	String sex;
	int age;
	@Autowired
	Location point;
	
	public User() {
		super();
	}
	public User(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public User(String name, String sex, int age, Location point) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.point = point;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Location getPoint() {
		return point;
	}
	public void setPoint(Location point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return " [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}	
	
	
}
