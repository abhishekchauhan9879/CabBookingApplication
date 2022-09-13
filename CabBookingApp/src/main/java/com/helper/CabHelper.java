package com.helper;

import org.springframework.stereotype.Service;

import com.model.Location;

@Service
public class CabHelper {

	public double find_distance(Location p1,Location p2) {
		double x1=p1.getX();double x2=p2.getX();
		double y1=p1.getY();double y2=p2.getY();
		
		return (Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1)));
	}
}
