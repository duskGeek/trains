package com.graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

import com.model.StationRoute;

public class DigraphTest {

	
	Digraph R=null;
	

	@Test
	public void testAdj() {
		R=new Digraph(Arrays.asList());
		
		R.adj("A");
	}

	@Test
	public void testGetAdjKeySet() {
		R=new Digraph(Arrays.asList());
		
		Set<String> set=R.getAdjKeySet();
		System.out.println(set);
	}

	@Test
	public void testLoadDigraph() {
		R=new Digraph(Arrays.asList());
    	
        R.loadDigraph(Arrays.asList(stationRoute,stationRouteAE,stationRouteDA));
    	System.out.println(R);
    	
    	for (String key : R.getAdjKeySet()) {
			for (StationRoute stationRoute: R.adj(key)) {
				System.out.println(stationRoute.getNext());
			}
		}
	}

	static StationRoute stationRoute=null;
	static StationRoute stationRouteAE=null;
	static StationRoute stationRouteDA=null;
	static {
    	stationRoute=new StationRoute();
    	stationRoute.setBegin("A");
    	stationRoute.setNext("B");
    	stationRoute.setDistance(5);
    	
    	stationRouteAE=new StationRoute();
    	stationRouteAE.setBegin("A");
    	stationRouteAE.setNext("E");
    	stationRouteAE.setDistance(5);
    	
        stationRouteDA=new StationRoute();
    	stationRouteDA.setBegin("D");
    	stationRouteDA.setNext("A");
    	stationRouteDA.setDistance(5);
	}
}
