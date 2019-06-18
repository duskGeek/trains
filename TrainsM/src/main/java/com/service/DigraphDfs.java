package com.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import com.graph.Digraph;
import com.model.StationRoute;

public class DigraphDfs {

	private Digraph digraph;
	private String beginStation; 
	private String endStation; 
	private String lastStation; 
	private int maxStationNum;
	private int maxDistance;
	private LinkedList<String> stationList;
	private HashMap<String,LinkedList<StationRoute>> totalRouteMap;
	private HashMap<String,LinkedList<StationRoute>> resultRouteMap;
	
	private int tempMinDistance=0;
	
	
	public DigraphDfs(Digraph digraph,LinkedList<String> stationList) {
		this.digraph=digraph;
		this.stationList=stationList;
		this.maxDistance=0;
		this.lastStation="";
	}
	public DigraphDfs(Digraph digraph,String beginStation
			,String endStation,int maxStationNum) {
		this.digraph=digraph;
		this.beginStation=beginStation;
		this.endStation=endStation;
		this.maxStationNum=maxStationNum;
		this.totalRouteMap=new HashMap<String,LinkedList<StationRoute>>();
	    this.resultRouteMap=new HashMap<String,LinkedList<StationRoute>>();
	}
	
	
	//通过最后一站判断是否成功搜索到路径
	public int searchDistanceByFixedRoute(String station,String lastStion) {
		 if(!checkFixedRouteParam()) {
			 return 0;
		 }
		 
		this.dfsFixPath(station);
		
		if(this.lastStation.equals(lastStion)) {
			return maxDistance;
		}
		return 0;
	}
	
	//通过最大深度判断是否继续搜索，结果返回所有找到的路线
	public HashMap<String,LinkedList<StationRoute>> searchRouteByStationNum(
			String beginStation,int curStationNum,String routeKey) {
		if(!checkSearchRoutParam()) {
			 return new HashMap<String,LinkedList<StationRoute>>();
		 }
		
		this.dfsRoute(beginStation, curStationNum, routeKey);
		
		return resultRouteMap;
	}
	
	//将最短距离保存至临时变量中，取到比临时变量距离更短时覆盖。
	//初始化边界为所有边的距离相加，即假设存在一个所有边的循环
    public int dfsMinRoute() {
    	if(!checkSearchRoutParam()) {
			 return 0;
		 }
    	
    	for (String key : digraph.getAdjKeySet()) {
    		maxDistance+=this.getDistance(digraph.adj(key));
    		tempMinDistance=maxDistance;
		}
		
    	this.dfsFixedDistance(beginStation, 1, beginStation);
    	
		return tempMinDistance;
	}
    
    //在所有返回的路径中排除超过最大距离路径
    public int dfsRoutes(int maxDistance) {
    	if(!checkSearchRoutParam()) {
			 return 0;
		 }
    	
    	this.maxDistance=maxDistance;
    	dfsFixedDistance(beginStation, 1, beginStation);
    	
    	int routesNum=0;
    	for (Entry<String, LinkedList<StationRoute>> route : resultRouteMap.entrySet()) {
    		int distance=this.getDistance(route.getValue());
    		if(maxDistance>this.getDistance(route.getValue())) {
    			routesNum++;
    		}
		}
    	return routesNum;
    }
    
  //每次找到匹配站点覆盖maxDistance和lastStation
    private void dfsFixPath(String station) {
		String nextStion=stationList.poll();
		if(nextStion==null) {
			return ;
		}
		for (StationRoute stationRoute : digraph.adj(station)) {
			
			if(stationRoute.getNext().equals(nextStion)) {
				maxDistance+=stationRoute.getDistance();
				lastStation=nextStion;
				dfsFixPath(nextStion);
			}
		}
	}
    
    //记录所有搜索过的路径，只有符合条件的路径才会放到结果集中，每次递归会将路径Key传入下一层
    private void dfsRoute(String beginStation,int curStationNum,String routeKey) {
		
		if(curStationNum>maxStationNum||digraph.adj(beginStation)==null) {
			return ;
		}
		
		for (StationRoute stationRoute : digraph.adj(beginStation)) {
			LinkedList<StationRoute> routeList =null;
			
			if(totalRouteMap.get(routeKey)!=null) {
				routeList=new LinkedList<StationRoute>(totalRouteMap.get(routeKey));
			}else {
				routeList=new LinkedList<StationRoute>();
			}
			
			routeList.add(stationRoute);
			
			StringBuffer nextRouteKey=new StringBuffer(routeKey);
			
			nextRouteKey.append("-"+stationRoute.getNext());
			
			totalRouteMap.put(nextRouteKey.toString(), routeList);

			if(endStation.equals(stationRoute.getNext())) {
				resultRouteMap.put(nextRouteKey.toString(), routeList);
			}
			
			dfsRoute(stationRoute.getNext(), curStationNum+1, nextRouteKey.toString());
		}
	}

    //判断当前路径是否超过最大距离，超过返回。
    private void dfsFixedDistance(String beginStation,int curStationNum,String routeKey) {
    	
    	LinkedList<StationRoute> curRouteList =null;
    	
    	if(totalRouteMap.get(routeKey)!=null) {
    		curRouteList=new LinkedList<StationRoute>(totalRouteMap.get(routeKey));
		}else {
			curRouteList=new LinkedList<StationRoute>();
		}
    	if(this.getDistance(curRouteList)>=maxDistance||
    			digraph.adj(beginStation)==null) {
    		return ;
    	}
    	
    	for (StationRoute stationRoute : digraph.adj(beginStation)) {
			LinkedList<StationRoute> routeList =new LinkedList<StationRoute>(curRouteList);

			routeList.add(stationRoute);
			
			StringBuffer nextRouteKey=new StringBuffer(routeKey);
			
			nextRouteKey.append("-"+stationRoute.getNext());
			
			totalRouteMap.put(nextRouteKey.toString(), routeList);

			if(endStation.equals(stationRoute.getNext())) {
				int distance=this.getDistance(routeList);
				if(distance<tempMinDistance) {
					//找到更小距离时覆盖
					tempMinDistance=distance;
				}
				resultRouteMap.put(nextRouteKey.toString(), routeList);
			}
			
			dfsFixedDistance(stationRoute.getNext(), curStationNum+1, nextRouteKey.toString());
		}
    }
    
    
    
    private int getDistance(Iterable<StationRoute> routeList){
    	int distance=0;
    	
    	for (StationRoute stationRoute : routeList) {
    		distance+=stationRoute.getDistance();
		}
    	
    	return distance;
    }
    
    private boolean checkFixedRouteParam() {
		if(this.digraph==null) {
			System.out.println("图未初始化");
			return false;
		}
		
		if(this.stationList==null) {
			System.out.println("路径列表未初始化");
			return false;
		}
		
		if(this.lastStation==null)
		{
			System.out.println("最后站点未初始化");
			return false;
		}
		return true;
	}
    public boolean checkSearchRoutParam() {
		if(this.digraph==null) {
			System.out.println("图未初始化");
			return false;
		}
		if(this.beginStation==null){
			System.out.println("开始站点未初始化");
			return false;
		}
		if(this.endStation==null){
			System.out.println("到达站点未初始化");
			return false;
		}
		if(this.totalRouteMap==null){
			System.out.println("总路径地图未初始化");
			return false;
		}
		if(this.resultRouteMap==null){
			System.out.println("结果地图未初始化");
			return false;
		}
		return true;
	}
	
}
