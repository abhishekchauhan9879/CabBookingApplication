package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.exception.AppException;
import com.model.*;
import com.service.CabService;

@ComponentScan(basePackages = "com.*")
@SpringBootApplication
public class CabBookingApplication {

	@Autowired
	private CabService cabService;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		try {
			ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(CabBookingApplication.class);
			CabBookingApplication application = context.getBean(CabBookingApplication.class);
			application.runApplication();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runApplication() throws AppException {
		try {
			cabService.predefinedSampleInputs();
			cabService.showMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
