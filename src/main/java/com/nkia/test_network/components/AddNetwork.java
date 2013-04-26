package com.nkia.test_network.components;

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
public class AddNetwork extends NetworkComponent {
	
	@Inject
	private Session session;

	@Inject
	private Request request;


	/*
	 * Add Network
	 */
	@CommitAfter
	public StreamResponse onAddNetwork() {
		 	
		System.out.println("====================================>onAdd");
		
		JSONObject myData = new JSONObject();
		Network network = new Network();
		try{
			
			network.name = request.getParameter("name");
			network.hostname = request.getParameter("hostname");
			network.ipAddress = request.getParameter("ipAddress");
			network.community = request.getParameter("community");
			network.description = request.getParameter("description");

			System.out.println("server====================================>" + network.toString());
			
			session.persist(network);
	
			myData.put("result", "success");
			myData.put("message", "Append Network");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}


	/*
	 * Add Networks
	 */
	@CommitAfter
	public StreamResponse onAddNetworks() {
		 	
		System.out.println("====================================>onAddNetworks");
		
		JSONObject myData = new JSONObject();
		Network network = null;
		try{
			
			
			for(int i=0; i < 10000 ;i++){

				network.name = Integer.toString(i);
				network.hostname = Integer.toString(i);
				network.ipAddress = Integer.toString(i);
				network.description = Integer.toString(i);

				
				session.persist(network);
			}
	
			myData.put("result", "success");
			myData.put("message", "Append Network");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}


	/*
	 * Delete Networks
	 */
	@CommitAfter
	public StreamResponse onDeleteNetworks() {
		 	
		
		JSONObject myData = new JSONObject();
		Network network = null;
		try{
			
			
			for(int i=0; i < 10000 ;i++){

				network = (Network)session.load(Network.class, Long.parseLong(Integer.toString(i)));				
				session.delete(network);
			}
	
			myData.put("result", "success");
			myData.put("message", "Append Network");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}
}
