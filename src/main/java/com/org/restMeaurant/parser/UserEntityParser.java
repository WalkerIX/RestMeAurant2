package com.org.restMeaurant.parser;


import com.google.appengine.api.datastore.Entity;
import com.org.restMeaurant.data.User;

public class UserEntityParser {

    public final static String UserNameField = "userName";
    public final static String UserFirstNameField = "userFirstName";

    private UserEntityParser() {
    }

    public static Entity userToEntity(User user) {
        if(user.getUserId().isEmpty()){
            return new Entity(User.INVALID, User.INVALID);
        }
        return new Entity(User.TYPE, user.getUserId());
    }

    public static User entityToUser(Entity entity) {
        String userName = !entity.hasProperty(UserNameField) ? "" : (String) entity.getProperty(UserNameField);
        String userFirstName = !entity.hasProperty(UserFirstNameField) ? "" : (String) entity.getProperty(UserFirstNameField);
        return new User(userName, userFirstName);
    }
}
