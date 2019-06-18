package com.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import com.graph.Digraph;
import com.model.StationRoute;

public class DigraphDfsTest {
	
	@Test
	public void testSearchDistanceByFixedRoute() {
		int distance;
		
    	LinkedList<String> path=new LinkedList<String>(Arrays.asList("A","B","C"));

		DigraphDfs digraphDfs=new DigraphDfs(new Digraph(stationRouteList),path);
		
		distance=digraphDfs.searchDistanceByFixedRoute(path.poll(),"C");
		
		System.out.println(distance);
		
	}

	@Test
	public void testSearchRouteByStationNum() {

		HashMap<String,LinkedList<StationRoute>>  resultRouteMap=new HashMap<String,LinkedList<StationRoute>>();
		
		DigraphDfs digraphDfs=new DigraphDfs(new Digraph(stationRouteList), "A", "C", 1);
		
		resultRouteMap=digraphDfs.searchRouteByStationNum("A", 1, "A");
		
		System.out.println(resultRouteMap);
	}

	@Test
	public void testDfsMinRoute() {
		
		
		DigraphDfs digraphDfs=new DigraphDfs(new Digraph(stationRouteList), "A", "C", 0);
		
		int minDistance=digraphDfs.dfsMinRoute();
		System.out.println(minDistance);
	}

	@Test
	public void testDfsRoutes() {
		DigraphDfs digraphDfs=new DigraphDfs(new Digraph(stationRouteList), "A", "C", 0);
		   
		int routeNum= digraphDfs.dfsRoutes(10);
		   
		System.out.println(routeNum);
	}
	
	static LinkedList<StationRoute> stationRouteList=null;
	
	static {
		StationRoute stationRoute=new StationRoute();
    	stationRoute.setBegin("A");
    	stationRoute.setNext("B");
    	stationRoute.setDistance(5);
    	
    	StationRoute stationRouteBC=new StationRoute();
    	stationRouteBC.setBegin("B");
    	stationRouteBC.setNext("C");
    	stationRouteBC.setDistance(4);
    	
    	
    	StationRoute stationRouteCD=new StationRoute();
    	stationRouteCD.setBegin("C");
    	stationRouteCD.setNext("D");
    	stationRouteCD.setDistance(8);
		
    	StationRoute stationRouteDE=new StationRoute();
    	stationRouteDE.setBegin("A");
    	stationRouteDE.setNext("C");
    	stationRouteDE.setDistance(6);
    	
    	stationRouteList=new LinkedList<>(Arrays.asList(
    			stationRoute,stationRouteBC,stationRouteCD,stationRouteDE));
	}
}
