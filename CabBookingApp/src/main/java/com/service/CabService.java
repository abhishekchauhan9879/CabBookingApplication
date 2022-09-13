package com.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.exception.AppException;
import com.model.Driver;
import com.model.Location;
import com.model.User;
import com.repository.CabRepository;

@Service
public class CabService {

	@Autowired
	CabRepository cabRepo;

	@Qualifier("eligibleDriver")
	List<Driver> eligibleDriver;

	@Autowired
	@Qualifier("usersList")
	private List<User> usersList;
	@Autowired
	@Qualifier("driverList")
	private List<Driver> driversList;

	Scanner sc = new Scanner(System.in);

	public void predefinedSampleInputs() {
		usersList.add(new User("Abhishek", "M", 23));
		update_userLocation("Abhishek", new Location(0, 0));
		usersList.add(new User("Rahul", "M", 29));
		update_userLocation("Rahul", new Location(10, 0));
		usersList.add(new User("Nandini", "F", 22));
		update_userLocation("Nandini", new Location(15, 6));
		driversList.add(new Driver("Driver1", "M", 22, "Swift", "KA-01-12345", new Location(10, 1)));
		driversList.add(new Driver("Driver2", "M", 29, "Swift", "KA-01-12345", new Location(11, 10)));
		driversList.add(new Driver("Driver3", "M", 24, "Swift", "KA-01-12345", new Location(5, 3)));

	}

	public void showMenu() {
		System.out.println("************************* Welcome to Cab Booking Application **************************");
		System.out.println("1.Login as user\n2.Login as Driver\n3.Exit ");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		sc.nextLine();
		switch (x) {
		case 1:
			showUserMenu();
			break;
		case 2:
			showDriverMenu();
			break;
		case 3:
			System.out.println("Thank you for Using Cab Application !!! Have a good day");
			System.out.println("This Application is developed by Abhishek Chauhan");
			
			System.exit(0);
			break;
		default:
			System.out.println("Please enter Valid Choice ");
			showMenu();
			break;
		}
	}

	private void showDriverMenu() {
		System.out.println("************************* Driver Menu ***************");
		System.out.println(
				"1.Add Driver Details \n2.Update Driver Location\n3.Change Driver Availability status\n4.View Total Earning\n5.Exit to Main Menu ");
		int x = sc.nextInt();
		sc.nextLine();
		switch (x) {
		case 1:
			addDriverDetails();
			showDriverMenu();
			break;
		case 2:
			System.out.println("Enter the Driver Name :");
			String driverName = sc.nextLine();
			System.out.println("Enter the New Location :");
			System.out.println("Please enter your X Coordinate :");
			int a = sc.nextInt();
			System.out.println("Please enter your Y Coordinate :");
			int b = sc.nextInt();
			sc.nextLine();
			update_driverLocation(driverName, new Location(a, b));
			showDriverMenu();
			break;
		case 3:
			System.out.println("Enter the Driver Name :");
			driverName = sc.nextLine();
			System.out.println("Enter the updated Driver Name : True/False");
			String status = sc.nextLine();
			change_driver_status(driverName, status);
			showDriverMenu();
			break;
		case 4:
			find_total_earning();
			showDriverMenu();
			break;
		case 5:
			showMenu();
			break;
		default:
			System.out.println("Please enter Valid Choice ");
			showDriverMenu();
			break;
		}
	}

	private void showUserMenu() {
		System.out.println("************************* User Menu ***************");
		System.out.println(
				"1.Add User Details \n2.Update User Details\n3.Update Location\n4.Book your ride\n5.Exit to Main Menu ");
		int t = sc.nextInt();
		sc.nextLine();
		switch (t) {
		case 1:
			addUserDetails();
			break;
		case 2:
			System.out.println("Please enter your Name to Continue:");
			String userName = sc.nextLine();
			update_user(userName);
			showUserMenu();
			break;
		case 3:
			System.out.println("Please enter your Name to Continue:");
			userName = sc.nextLine();
			System.out.println("Please enter your Updated Location to Continue:");
			System.out.println("Please enter your X Coordinate :");
			int x = sc.nextInt();
			System.out.println("Please enter your Y Coordinate :");
			int y = sc.nextInt();
			if (update_userLocation(userName, new Location(x, y))) {
				System.out.println("Location updated: " + x + "," + y);
			}
			showUserMenu();
			break;
		case 4:
			bookTheRide();
			showUserMenu();
			break;
		case 5:
			showMenu();
			break;
		default:
			System.out.println("Please enter Valid Choice ");
			showUserMenu();
			break;
		}
	}

	private void addUserDetails() {
		try {
			System.out.println("Enter the User Details");
			System.out.println("Enter your Name");
			String name = sc.nextLine();
			if (IsUserNameAvailable(name)) {
				throw new AppException("UserName already exists");
			}
			System.out.println("Enter your Sex(M/F)");
			String sex = sc.nextLine();
			if (sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F")) {
				System.out.println("Enter your age");
				int age = sc.nextInt();
				sc.nextLine();
				if (age > 0) {
					usersList.add(cabRepo.add_user(name, sex, age));
					System.out.println("Success!!! User added");
					System.out.println("printing user list:");
					for (User x : usersList) {
						System.out.println(x);
					}
					showUserMenu();
				} else {
					throw new AppException("Age should be greater than 0");
				}
			} else {
				throw new AppException("Sex should be M or F");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			showUserMenu();
		}
	}

	private void addDriverDetails() {
		try {
			System.out.println("Enter the Driver Details");
			System.out.println("Enter your Name");
			String name = sc.nextLine();
			if (!isDriverNameAvailable(name)) {
				System.out.println("Enter your Sex(M/F)");
				String sex = sc.nextLine();
				if (sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F")) {
					System.out.println("Enter your age");
					int age = sc.nextInt();
					sc.nextLine();
					if (age > 0) {
						System.out.println("Enter your Vehicle Name");
						String vehicleName = sc.nextLine();
						if (!vehicleName.isEmpty()) {
							System.out.println("Enter your Vehicle Number");
							String vehicleNumber = sc.nextLine();
							if (!vehicleNumber.isEmpty()) {
								System.out.println("Enter your Location:");
								System.out.println("Please enter your X Coordinate");
								int x = sc.nextInt();
								System.out.println("Please enter your Y Coordinate");
								int y = sc.nextInt();
								sc.nextLine();
								driversList.add(cabRepo.add_driver(name, sex, age, vehicleName, vehicleNumber,
										new Location(x, y)));
								System.out.println("Success!!! Driver Details Added");
							} else {
								throw new AppException("Vehicle Number should not be empty");
							}
						} else {
							throw new AppException("Vehicle Name should not be empty");
						}
					} else {
						throw new AppException("Age should be greater than 0");
					}
				} else {
					throw new AppException("Sex should be M or F");
				}

			} else {
				throw new AppException("Driver Name already exists");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	private void update_user(String userName) {
		try {
			boolean isNameAvailable = false;
			for (User user : usersList) {
				if (user.getName().equalsIgnoreCase(userName)) {
					isNameAvailable = true;
					System.out.println("Current Details : " + user);
					System.out.println("Enter the Updated User Details");
					System.out.println("Enter your Sex(M/F)");
					String sex = sc.nextLine();
					if (sex.equalsIgnoreCase("M") || sex.equalsIgnoreCase("F")) {
						System.out.println("Enter your age");
						int age = Integer.parseInt(sc.nextLine());
						if (age > 0) {
							user.setAge(age);
							user.setSex(sex);
							System.out.println("Success!!!");
							System.out.println("Updated Details: " + user);
						} else {
							throw new AppException("Age should be greater than 0");
						}
					} else {
						throw new AppException("Sex should be M or F");
					}
				}
			}

			if (!isNameAvailable) {
				throw new AppException("User Name is not available . Please add your details to proceed.");
			}
			showUserMenu();
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}

	}

	private boolean update_userLocation(String userName, Location p2) {
		try {
			if (IsUserNameAvailable(userName)) {
				for (User user : usersList) {
					if (user.getName().equalsIgnoreCase(userName)) {
						user.setPoint(p2);
						return true;
					}
				}
			} else {
				throw new AppException("User Name is not available . Please add your details to proceed.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	private void bookTheRide() {

		try {
			System.out.println("Please enter your Name to Continue:");
			String userName = sc.nextLine();
			boolean isNameAvailable = false;
			for (User user : usersList) {
				if (user.getName().equalsIgnoreCase(userName)) {
					isNameAvailable = true;
					System.out.println("Please enter your Source Coordinates to Continue:");
					System.out.println("Please enter your X Coordinate");
					int x = sc.nextInt();
					System.out.println("Please enter your Y Coordinate");
					int y = sc.nextInt();
					Location p1 = new Location(x, y);
					System.out.println("Please enter your Destination Coordinates to Continue:");
					System.out.println("Please enter your X Coordinate");
					x = sc.nextInt();
					System.out.println("Please enter your Y Coordinate");
					y = sc.nextInt();
					sc.nextLine();
					Location p2 = new Location(x, y);
					find_ride(user.getName(), p1, p2);
				}
			}
			if (!isNameAvailable) {
				throw new AppException("User Name is not available . Please add your details to proceed.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
			showUserMenu();
		}
	}

	private void find_ride(String userName, Location p1, Location p2) throws AppException {
		eligibleDriver = cabRepo.getEligibleDrivers(p1);
		try {
			if (eligibleDriver.size() > 0) {
				int i = 1;
				System.out.println("Please select 1 from Available Driver: ");
				for (Driver d : eligibleDriver) {
					System.out.println(i + "." + d.getName() + " [Available]");
					i++;
				}
				System.out.println("Please Enter the Driver Name to continue :");
				String driverName = sc.nextLine();
				Driver driverSelected = new Driver();
				boolean isEnteredNameAvailable = false;
				for (Driver x : eligibleDriver) {
					if (x.getName().equalsIgnoreCase(driverName)) {
						isEnteredNameAvailable = true;
						driverSelected = x;
					}
				}
				if (isEnteredNameAvailable) {
					chooseRide(userName, driverName);
					int dist = (int) Math.round(cabRepo.find_dist(p1, p2));

					System.out.println("Calculating Bill Amount!!!\nRide Ended bill amount $" + dist);
					int earning = driverSelected.getEarning() + dist;
					driverSelected.setEarning(earning);
					update_userLocation(userName, p2);
					update_driverLocation(driverName, p2);
					change_driver_status(driverName, "false");
					showUserMenu();
				} else {
					throw new AppException("\nPlease check Driver's Name and Try again Later");
				}

			} else {
				throw new AppException("\nNo ride found [Since all the driver are more than 5 units away from user]");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			find_ride(userName, p1, p2);
		}
	}

	private void chooseRide(String userName, String driverName) {
		try {
			if (!userName.isEmpty() && !driverName.isEmpty()) {
				System.out.println("Hurray !!! " + userName + " Ride Started.");
			} else {
				throw new AppException("UserName/DriverName can't be empty.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}

	}

	private void update_driverLocation(String driverName, Location p2) {
		boolean isDriverNameAvailable = false;
		try {
			for (Driver driver : driversList) {
				if (driver.getName().equalsIgnoreCase(driverName)) {
					isDriverNameAvailable = true;
					driver.setPoint(p2);
				}
			}
			if (isDriverNameAvailable) {
				System.out.println("Driver Location Updated!!!" + " New Location:" + p2);
			} else {
				throw new AppException("Driver Name not available.");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private void change_driver_status(String driverName, String status) {
		boolean isDriverNameAvailable = false;
		try {
			if (status.equalsIgnoreCase("True") || status.equalsIgnoreCase("False")) {
				for (Driver driver : driversList) {
					if (driver.getName().equalsIgnoreCase(driverName)) {
						isDriverNameAvailable = true;
						driver.setAvailable(status.equalsIgnoreCase("True"));
					}
				}
				if (isDriverNameAvailable) {
					System.out.println("Driver Status Updated to " + status);
				} else {
					throw new AppException("Driver Name not available.");
				}
			} else {
				throw new AppException("Status should be True/False.");
			}

		} catch (AppException e) {
			System.out.println(e.getMessage());
		}

	}

	private void find_total_earning() {
		try {
			if (!driversList.isEmpty()) {
				for (Driver d : driversList) {
					System.out.println(d.getName() + " earn $" + d.getEarning());
				}
			} else {
				throw new AppException("Driver List is empty");
			}
		} catch (AppException e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean IsUserNameAvailable(String name) {
		for (User u : usersList) {
			if (u.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
	private boolean isDriverNameAvailable(String name) {
		for (Driver u : driversList) {
			if (u.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
}
