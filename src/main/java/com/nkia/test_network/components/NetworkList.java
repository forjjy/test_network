package com.nkia.test_network.components;

import java.util.List;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.hibernate.Session;

import com.nkia.test_core.common.JsonStreamResponse;
import com.nkia.test_network.base.NetworkComponent;
import com.nkia.test_network.entities.Network;


@Import(stack = "ExtJSStack")
public class NetworkList extends NetworkComponent {
	
	@Inject
	private Session session;

	@Inject
	private Request request;

	/*
	 * Network info
	 */
	public StreamResponse onNetworkInfo() {
		System.out.println("====================================>onNetworkInfo=====>");

		String id = request.getParameter("id");
		System.out.println("====================================>onNetworkInfo id=====>"+ id);
		
		Network network = (Network)session.load(Network.class, Long.parseLong(id));

		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(network);

		System.out.println("====================================>onNetworkInfo result=====>"+json.toString());
		
		return new JsonStreamResponse(json.toString());

	}
	
	/*
	 * Network List
	 */
	public StreamResponse onGetNetworks() {
		System.out.println("====================================>onGetNetworks=====>");
		List<Network> list = session.createCriteria(Network.class).list();

		
		//System.out.println("====================================>onGetNetworks list=====>"+list);
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		net.sf.json.JSONArray jsonArray = null;
		try{
			
			jsonArray = net.sf.json.JSONArray.fromObject(list);			
			
			//System.out.println("====================================>onGetNetworks jsonArray=====>"+jsonArray);
			
			json.put("root", jsonArray.toString());			

			//System.out.println("====================================>onGetNetworks json=====>"+json.toString());
			
		}catch(net.sf.json.JSONException e){
			System.out.println("====================================>net.sf.json.JSONException=====>");
			e.printStackTrace();
		}
		return new JsonStreamResponse(jsonArray.toString());

	}

	/*
	 * Delete Network
	 */
	@CommitAfter
	public StreamResponse onDelete() {
		System.out.println("====================================>onDelete=====>");

		String id = request.getParameter("id");
		System.out.println("====================================>onDelete id=====>"+ id);
		
		Network network = (Network)session.load(Network.class, Long.parseLong(id));

		JSONObject json = new JSONObject();
		
		if(network != null){
			session.delete(network);
			json.put("result", "success");
			json.put("message", "Delete Network");
		}else{
			json.put("result","failure");
		}

		System.out.println("====================================>onDelete result=====>"+json.toString());
		return new JsonStreamResponse(json.toString());

	}
	
	/*
	 * Update Network
	 */
	@CommitAfter
	StreamResponse onUpdateNetwork() {
		 	
		System.out.println("====================================>onUpdateNetwork");
		
		JSONObject myData = new JSONObject();
		try{


			Network network = (Network)session.load(Network.class, Long.parseLong(request.getParameter("id")));
			
			network.setName(request.getParameter("name"));
			network.setHostname(request.getParameter("hostname"));
			network.setIpAddress(request.getParameter("ipAddress"));
			network.setCommunity(request.getParameter("community"));
			network.setDescription( request.getParameter("description"));
			
			System.out.println("Network====================================>" + network.toString());
			
			session.saveOrUpdate(network);
	
			myData.put("result", "success");
			myData.put("message", "Update Network");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}
}
