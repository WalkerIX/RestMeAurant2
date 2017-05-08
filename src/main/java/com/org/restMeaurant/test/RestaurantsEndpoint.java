package com.org.restMeaurant.test;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.api.datastore.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Api(name = "restaurants", version = "v1")
public class RestaurantsEndpoint {
    @ApiMethod(name = "sayHi", httpMethod = ApiMethod.HttpMethod.POST)
    public Entity sayHi(@Named("name") String name) throws EntityNotFoundException {
        MyData data = new MyData();
        data.setMessage("Hi" + name);
        data.list = new ArrayList<>();
        data.list.add(new MySubData());
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity dataEntity = new Entity("DataLevel", "data");
        Key keyData = dataEntity.getKey();
        dataEntity.setProperty("Name", "Hi" + name);
        datastore.put(dataEntity);
        Entity subDataEntity = new Entity("level", keyData);
        subDataEntity.setProperty("Name", "SubLevel1");
        Entity subDataEntity2 = new Entity("level", keyData);
        subDataEntity2.setProperty("Name", "SubLevel2");

        datastore.put(subDataEntity);
        datastore.put(subDataEntity2);;

        Query placeQuery = new Query("level").setAncestor(keyData);
        List<Entity> listEntities = datastore.prepare(placeQuery).asList(FetchOptions.Builder.withDefaults());

        Entity unifyEntity = new Entity("result");

        Entity tom = new Entity("Person", "Tom");
        Key tomKey = tom.getKey();
        datastore.put(tom);

        Entity weddingPhoto = new Entity("Photo", tomKey);
        weddingPhoto.setProperty("imageURL", "http://domain.com/some/path/to/wedding_photo.jpg");

        Entity babyPhoto = new Entity("Photo", tomKey);
        babyPhoto.setProperty("imageURL", "http://domain.com/some/path/to/baby_photo.jpg");

        Entity dancePhoto = new Entity("Photo", tomKey);
        dancePhoto.setProperty("imageURL", "http://domain.com/some/path/to/dance_photo.jpg");

        Entity campingPhoto = new Entity("Photo");
        campingPhoto.setProperty("imageURL", "http://domain.com/some/path/to/camping_photo.jpg");

        List<Entity> photoList = Arrays.asList(weddingPhoto, babyPhoto, dancePhoto, campingPhoto);
        datastore.put(photoList);

        Query photoQuery = new Query("Photo").setAncestor(tomKey);

        // This returns weddingPhoto, babyPhoto, and dancePhoto,
        // but not campingPhoto, because tom is not an ancestor
        List<Entity> results =
                datastore.prepare(photoQuery).asList(FetchOptions.Builder.withDefaults());
        return unifyEntity;
    }
}
