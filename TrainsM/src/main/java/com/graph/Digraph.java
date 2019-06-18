package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.model.StationRoute;

public class Digraph {
    private int V;
    private int E;
    private Map<String,LinkedList<StationRoute>> adj;
    
    public Digraph(List<StationRoute> stationRouteList) {
       this.E=0;
       adj=new HashMap<String,LinkedList<StationRoute>>();
       
       this.loadDigraph(stationRouteList);
       
       this.V=adj.keySet().size();
    }
    public int V() {
        return V;
    }
    public int E() {
        return E;
    }
  //添加一条边v->w,由于是有向图只要添加一条边就可以了
    public void addEdge(String v,StationRoute w) {
    	if(adj.get(v)==null) {
    		adj.put(v, new LinkedList<StationRoute>());
    	}
        adj.get(v).add(w);
        E++;
    }
    public Iterable<StationRoute> adj(String v) {
        return adj.get(v);
    }
    
    public Set<String> getAdjKeySet(){
    	return adj.keySet();
    }
    
    
    public void loadDigraph(List<StationRoute> stationRouteList) {
    	stationRouteList=stationRouteList==null?new ArrayList<>():stationRouteList;
    	for (StationRoute stationRoute : stationRouteList) {
			String v=stationRoute.getBegin();
			this.addEdge(v, stationRoute);
		}
    }
}
