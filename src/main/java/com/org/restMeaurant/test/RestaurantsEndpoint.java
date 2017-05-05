package com.org.restMeaurant.test;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.*;

import java.util.ArrayList;


@Api(name = "restaurants", version = "v1")
public class RestaurantsEndpoint {
    @ApiMethod(name = "sayHi", httpMethod = ApiMethod.HttpMethod.POST)
    public MyData sayHi(@Named("name") String name) throws EntityNotFoundException {
        MyData data = new MyData();
        data.message = "Hi"+name;
        data.list = new ArrayList<>();
        data.list.add(new MySubData());
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity myDataEntity = new Entity("mydata","big");
        myDataEntity.setProperty("gender", "male");
        System.out.println("Put my data to dataStore");
        datastore.put(myDataEntity);
        Key key = KeyFactory.createKey("mydata","big");
        Entity getNew = datastore.get(key);
        System.out.println("Get my data from dataStore"+getNew.getProperty("gender"));
        return data;
    }
}
