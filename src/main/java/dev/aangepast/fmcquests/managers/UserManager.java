package dev.aangepast.fmcquests.managers;

import java.util.HashMap;

public class UserManager {

    HashMap<User, String> users = new HashMap<>();

    public HashMap<User, String> getUsers(){
        return users;
    }
    public void addUser(User user, String uuid){
        users.put(user, uuid);
    }
    public void removeUser(User user){
        users.remove(user);
    }
    public boolean containsUser(String uuid){
        return users.containsKey(uuid);
    }

}
