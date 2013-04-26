package com.nkia.test_network.pages;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.hibernate.Session;

import com.nkia.test_core.common.JsonStreamResponse;
import com.nkia.test_network.entities.Network;


@Import(stack = "ExtJSStack")
public class AddNetworkPage {


	
	@Inject
	private Session session;

	/*
	 * Add Servers
	 */
	@CommitAfter
	public StreamResponse onAddServers() {
		 	
		System.out.println("====================================>onAddServers");
		
		JSONObject myData = new JSONObject();
		try{
			
			
			for(int i=0; i < 1000 ;i++){

				Network network = new Network();
				network.name = Integer.toString(i);
				network.hostname = Integer.toString(i);
				network.ipAddress = Integer.toString(i);
				network.description = Integer.toString(i);

				
				session.persist(network);
			}
	
			myData.put("result", "success");
			myData.put("message", "Append Server");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}


	/*
	 * Delete Servers
	 */
	@CommitAfter
	public StreamResponse onDeleteServers() {		 	
		
		JSONObject myData = new JSONObject();
		Network network = new Network();
		try{
			
			
			for(int i=0; i < 1000 ;i++){

				network = (Network)session.load(Network.class, Long.parseLong(Integer.toString(i)));	
				try{
				if(network != null)session.delete(network);
				}catch(Exception e){
					
				}
			}
	
			myData.put("result", "success");
			myData.put("message", "Append Server");
			
		}catch(Exception e){
			e.printStackTrace();
			myData.put("result", "failure");
		}
		
		return new JsonStreamResponse(myData.toString());

	}
}
 