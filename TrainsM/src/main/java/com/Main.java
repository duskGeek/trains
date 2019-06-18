package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.control.SearchRoute;
import com.model.StationRoute;
import com.utils.PropertiesUtil;

public class Main {

	public static void main(String[] args) {
		try { 
			System.out.println("#####load stationRoute#####");
			PropertiesUtil.readProperties("stationRoute.properties");
			System.out.println("#####load stationRoute done###");
		} catch (Exception e) {
			System.out.println("load stationRoute error！");
			return ;
		}
		List<String> routeList=PropertiesUtil.getProps();
		
//		String routeInput="AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
//		
//		List<String> routeList=Arrays.asList(routeInput.split(","));
		
		List<StationRoute> stationRouteList=new ArrayList<StationRoute>();
		for (String routeInfo : routeList) {
			char[] stationRouteChar= routeInfo.toCharArray();
			
			StationRoute stationRoute=new StationRoute();
			stationRoute.setBegin(String.valueOf(stationRouteChar[0]));
	    	stationRoute.setNext(String.valueOf(stationRouteChar[1]));
	    	stationRoute.setDistance(Integer.parseInt(stationRouteChar[2]+""));
	    	stationRouteList.add(stationRoute);
		}

    	SearchRoute searchRoute=new SearchRoute(stationRouteList);
    	
    	String distance=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","B","C")));
    	System.out.println(distance);
    	distance=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","D")));
    	System.out.println(distance);
    	distance=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","D","C")));
    	System.out.println(distance);
    	distance=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","E","B","C","D")));
        System.out.println(distance);
        distance=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","E","D")));
        System.out.println(distance);
    	int val=searchRoute.searchRouteByStationNum("C", "C", 3);
    	System.out.println(val);
    	val=searchRoute.searchRouteByAccurateStationNum("A", "C", 4);
    	System.out.println(val);
    	val=searchRoute.searchMinDistance("A", "C");
    	System.out.println(val);
    	val=searchRoute.searchMinDistance("B", "B");
    	System.out.println(val);
    	val=searchRoute.searchRouteByDistance("C", "C", 30);
    	System.out.println(val);
	}
}
