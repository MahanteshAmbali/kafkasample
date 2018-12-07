package com.target.kafka.serivceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl {

    private Map<String, Integer> userMap;

    public UserServiceImpl() {
        userMap = new HashMap<String, Integer>();
        userMap.put("Tom", 1);
        userMap.put("Mahantesh", 2);
        userMap.put("Soumya", 3);
        userMap.put("Samba", 4);
        userMap.put("Apple", 5);
    }

    public Integer findUser(String userName) {
        return userMap.get(userName);
    }

    public List<String> findAllUsers() {
        return new ArrayList<String>(userMap.keySet());
    }
}
