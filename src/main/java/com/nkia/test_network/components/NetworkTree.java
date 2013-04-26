package com.nkia.test_network.components;

import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.collections.list.TreeList;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.tree.TreeNode;
import org.hibernate.Session;

import com.nkia.test_core.common.JsonStreamResponse;
import com.nkia.test_network.base.NetworkComponent;
import com.nkia.test_network.entities.Network;

@Import(stack = "ExtJSStack")
public class NetworkTree extends NetworkComponent
{
	
	@Inject
	private Session session;

	@Inject
	private Request request;

	@Parameter
	@Property
	private String title;	
	
	public StreamResponse onGetLeafs(){
		
		System.out.println("====================================>onGetLeafs=====>" + request.getParameter("id"));
		
		return onGetNetworks();
	}

	
	/*
	 * Network List
	 */
	public StreamResponse onGetNetworks() {
		System.out.println("====================================>onGetNetworks=====>");
		List<Network> list = session.createCriteria(Network.class).list();
		TreeList treeList = new TreeList();

		
		//System.out.println("====================================>onGetNetworks list=====>"+list);
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		net.sf.json.JSONArray jsonArray = null;
		try{
			
			if(list != null){
				
				int size = list.size();
				for (Iterator<Network> iterator = list.iterator(); iterator.hasNext(); ){
					Network network = iterator.next();
					TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
					
					treeMap.put("text", network.name);
					treeMap.put("data", net.sf.json.JSONObject.fromObject(network));
					//treeMap.put("leaf", false);
					
					treeList.add(treeMap);
				}
			}
			
			jsonArray = net.sf.json.JSONArray.fromObject(treeList);			
			
			json.put("items", jsonArray.toString());			

			//System.out.println("====================================>onGetNetworks json=====>"+json.toString());
			
		}catch(net.sf.json.JSONException e){
			System.out.println("====================================>net.sf.json.JSONException=====>");
			e.printStackTrace();
		}
		return new JsonStreamResponse(jsonArray.toString());

	}	

	/*
	 * server info
	 */
	public StreamResponse onNetworkInfo() {
		System.out.println("====================================>onNetworkInfo=====>");

		String id = request.getParameter("id");
		System.out.println("====================================>onNetworkInfo id=====>"+ id);

		TreeList treeList = new TreeList();
		Network network = (Network)session.load(Network.class, Long.parseLong(id));
		

		TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
		
		treeMap.put("text", network.name);
		treeMap.put("value", network.name);
		treeMap.put("leaf", true);
		treeList.add(treeMap);
		
		treeMap.put("text", network.hostname);
		treeMap.put("value", network.hostname);
		treeMap.put("leaf", true);
		treeList.add(treeMap);
		
		treeMap.put("text", network.ipAddress);
		treeMap.put("value", network.ipAddress);
		treeMap.put("leaf", true);
		treeList.add(treeMap);
		
		treeMap.put("text", network.community);
		treeMap.put("value", network.community);
		treeMap.put("leaf", true);
		treeList.add(treeMap);
		
		treeMap.put("text", network.description);
		treeMap.put("value", network.description);
		treeMap.put("leaf", true);
		treeList.add(treeMap);

		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(treeList);

		System.out.println("====================================>onNetworkInfo result=====>"+json.toString());
		
		return new JsonStreamResponse(json.toString());

	}
	
}
