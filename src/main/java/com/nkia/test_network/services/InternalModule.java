package com.nkia.test_network.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.services.LibraryMapping;

public class InternalModule {
	public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("test_network", "com.nkia.test_network"));
	}
	
	public static void contributeHibernateEntityPackageManager(Configuration<String> configuration)
	{
		configuration.add("com.nkia.test_network.entities");
	}
}
