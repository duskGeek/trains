package com.control;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.graph.Digraph;
import com.model.StationRoute;
import com.service.DigraphDfs;

public class SearchRoute {

	private Digraph digraph;
	
	public SearchRoute(List<StationRoute> stationRouteList) {
		digraph=new Digraph(stationRouteList);
	}
	
	//输入固定路线，查询出距离
	public String searchDistanceByFixedRoute(LinkedList<String> stationList) {
		int distance; 
		
		DigraphDfs digraphDfs=new DigraphDfs(digraph,stationList);
		
		String lastStation=stationList.getLast();
		distance=digraphDfs.searchDistanceByFixedRoute(stationList.poll(),lastStation);
		
		return  distance==0?"NO SUCH ROUTE":String.valueOf(distance);
	}
	
	
	//输入起点和终点，限制站点数查询路线
	public int searchRouteByStationNum(String beginStation,String endStation,int StationNum) {
		HashMap<String,LinkedList<StationRoute>>  resultRouteMap=new HashMap<String,LinkedList<StationRoute>>();
		
		DigraphDfs digraphDfs=new DigraphDfs(digraph, beginStation, endStation, StationNum);
		
		resultRouteMap=digraphDfs.searchRouteByStationNum(beginStation, 1, beginStation);
		
		return resultRouteMap.entrySet().size();
	}
	
	//输入起点和终点，精确站点数查询路线
	public int searchRouteByAccurateStationNum(String beginStation,String endStation,int StationNum) {
		HashMap<String,LinkedList<StationRoute>>  resultRouteMap=new HashMap<String,LinkedList<StationRoute>>();
		
		DigraphDfs digraphDfs=new DigraphDfs(digraph, beginStation, endStation, StationNum);
		
		resultRouteMap=digraphDfs.searchRouteByStationNum(beginStation, 1, beginStation);
		
		int routeNum=0;
		for (Entry<String, LinkedList<StationRoute>> route : resultRouteMap.entrySet()) {
			LinkedList<StationRoute> stationRouteList=route.getValue();
			if(stationRouteList.size()==StationNum) {
				routeNum++;
			}
		}
		
		return routeNum;
	}
	
	//输入起点和终点，查询最短线路
   public int searchMinDistance(String beginStation,String endStation) {
	   
	   DigraphDfs digraphDfs=new DigraphDfs(digraph, beginStation, endStation, 0);
		
	   int minDistance=digraphDfs.dfsMinRoute();
	   
	   return minDistance;
	}
	
   //输入起点和终点，限制距离查询线路
   public int searchRouteByDistance(String beginStation,String endStation,int maxDistance) {
	   DigraphDfs digraphDfs=new DigraphDfs(digraph, beginStation, endStation, 0);
	   
	   int routeNum= digraphDfs.dfsRoutes(maxDistance);
	   
	   return routeNum;
	}
   
}
