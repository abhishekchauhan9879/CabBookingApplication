package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Driver {

	String name;
	String sex;
	int age;
	String vehicleName;
	String vehicleNumber;
	Boolean available;
	int earning;

	@Autowired
	Location point;

	public Driver() {
		super();
	}

	public Driver(String name, String sex, int age, String vehicleName, String vehicleNumber, Location point) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.vehicleName = vehicleName;
		this.vehicleNumber = vehicleNumber;
		this.point = point;
		this.available = true;
		earning = 0;
	}

	public int getEarning() {
		return earning;
	}

	public void setEarning(int earning) {
		this.earning = earning;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
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

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Location getPoint() {
		return point;
	}

	public void setPoint(Location point) {
		this.point = point;
	}

}
