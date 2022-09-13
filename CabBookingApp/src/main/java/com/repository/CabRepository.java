package com.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import com.exception.AppException;
import com.helper.CabHelper;
import com.model.Driver;
import com.model.Location;
import com.model.User;
import com.service.CabService;

@Repository
public class CabRepository {

	@Autowired
	CabHelper cabHelper;

	@Autowired
	CabService cabService;
	@Autowired
	@Qualifier("usersList")
	List<User> usersList;
	@Autowired
	@Qualifier("driverList")
	List<Driver> driversList;

	public User add_user(String name, String sex, int age) {
		User user = new User(name, sex, age);
		return user;
	}

	public void update_userLocation(User user, Location point) {
		user.setPoint(point);
	}

	public Driver add_driver(String name, String sex, int age, String vehicleName, String vehicleNumber,
			Location point) {
		return new Driver(name, sex, age, vehicleName, vehicleNumber, point);
	}

	@Bean("eligibleDriver")
	public List<Driver> getEligibleDrivers(Location p1) {
		List<Driver> eligibleDriverList = new ArrayList<Driver>();
		try {
			if (driversList != null) {
				for (Driver d : driversList) {
					double dist = cabHelper.find_distance(p1, d.getPoint());
					if (dist <= 5 && d.getAvailable()) {
						eligibleDriverList.add(d);
					}
				}
				return eligibleDriverList;
			} else {
				throw new AppException("Driver List is empty while checking eligible Drivers.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
		return eligibleDriverList;
	}

	public double find_dist(Location p1, Location p2) {
		return cabHelper.find_distance(p1, p2);
	}
}
