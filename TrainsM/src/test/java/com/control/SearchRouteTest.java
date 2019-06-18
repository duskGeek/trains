package com.control;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

import com.control.SearchRoute;
import com.model.StationRoute;

public class SearchRouteTest {
	
	static SearchRoute searchRoute=null;
	@Test
	public void testSearchDistanceByFixedRoute() {
		
		String d=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","B","C")));
    	System.out.println(d);
    	d=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","D")));
    	System.out.println(d);
    	d=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","D","C")));
    	System.out.println(d);
        d=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","E","B","C","D")));
        System.out.println(d);
        d=searchRoute.searchDistanceByFixedRoute(new LinkedList<>(Arrays.asList("A","E","D")));
        System.out.println(d);
	}

	@Test
	public void testSearchRouteByStationNum() {
		int c=searchRoute.searchRouteByStationNum("C", "C", 3);
    	System.out.println(c);
	}

	@Test
	public void testSearchRouteByAccurateStationNum() {
		
		int c=searchRoute.searchRouteByAccurateStationNum("A", "C", 4);
    	System.out.println(c);
	}

	@Test
	public void testSearchMinDistance() {
		int c=searchRoute.searchMinDistance("A", "C");
    	System.out.println(c);
    	c=searchRoute.searchMinDistance("B", "B");
    	System.out.println(c);
	}

	@Test
	public void testSearchRouteByDistance() {
		int c=searchRoute.searchRouteByDistance("C", "C", 30);
    	System.out.println(c);
	}

	static {
		StationRoute stationRoute=new StationRoute();
    	stationRoute.setBegin("A");
    	stationRoute.setNext("B");
    	stationRoute.setDistance(5);
    	
    	StationRoute stationRoute1=new StationRoute();
    	stationRoute1.setBegin("B");
    	stationRoute1.setNext("C");
    	stationRoute1.setDistance(4);
    	
    	StationRoute stationRoute2=new StationRoute();
    	stationRoute2.setBegin("C");
    	stationRoute2.setNext("D");
    	stationRoute2.setDistance(8);
    	
    	StationRoute stationRoute3=new StationRoute();
    	stationRoute3.setBegin("D");
    	stationRoute3.setNext("C");
    	stationRoute3.setDistance(8);
    	
    	StationRoute stationRoute4=new StationRoute();
    	stationRoute4.setBegin("D");
    	stationRoute4.setNext("E");
    	stationRoute4.setDistance(6);
    	
    	StationRoute stationRoute5=new StationRoute();
    	stationRoute5.setBegin("A");
    	stationRoute5.setNext("D");
    	stationRoute5.setDistance(5);
    	
    	StationRoute stationRoute6=new StationRoute();
    	stationRoute6.setBegin("C");
    	stationRoute6.setNext("E");
    	stationRoute6.setDistance(2);
    	
    	StationRoute stationRoute7=new StationRoute();
    	stationRoute7.setBegin("E");
    	stationRoute7.setNext("B");
    	stationRoute7.setDistance(3);
    	
    	StationRoute stationRoute8=new StationRoute();
    	stationRoute8.setBegin("A");
    	stationRoute8.setNext("E");
    	stationRoute8.setDistance(7);
    	
    	searchRoute=new SearchRoute(Arrays.asList(stationRoute,stationRoute1,stationRoute2
    			,stationRoute3,stationRoute4,stationRoute5,stationRoute6,stationRoute7,stationRoute8));
	}
}
