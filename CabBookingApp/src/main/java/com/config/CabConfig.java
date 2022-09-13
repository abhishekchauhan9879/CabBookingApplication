package com.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.model.Driver;
import com.model.User;

@Configuration
public class CabConfig {

	@Bean("driverList")
	public List<Driver> getDriverList(){
		List<Driver> driversList=new ArrayList<Driver>();
		return driversList;
	}
	@Bean("usersList")
	public List<User> getUserList(){
		List<User> usersList=new ArrayList<User>();
		return usersList;
	}
}
