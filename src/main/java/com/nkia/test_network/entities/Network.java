package com.nkia.test_network.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
public class Network implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4739141272421820002L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	public Long id;
	
	@Validate("required")
	public String name;

	public String hostname;
	
	public String ipAddress;
	
	public String community;

	public String description;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Server [id=" + id + ", name=" + name + ", hostname=" + hostname + ", ipAddress=" + ipAddress
				 + ", community=" + community + ", description=" + description + "]";
	}
	
	public String toJsonString() {
		return "{id:'" + id + "', name:'" + name + "', hostname:'" + hostname + "', ipAddress:'" + ipAddress
				 + "', community:'" + community+ "', description:'" + description +"'}";
	}
}
