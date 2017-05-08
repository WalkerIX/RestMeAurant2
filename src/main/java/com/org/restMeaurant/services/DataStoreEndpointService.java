package com.org.restMeaurant.services;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class DataStoreEndpointService {
    protected DatastoreService datastoreService;

    public DataStoreEndpointService() {
        datastoreService = DatastoreServiceFactory.getDatastoreService();
    }

}
