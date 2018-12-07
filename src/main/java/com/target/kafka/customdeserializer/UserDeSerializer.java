package com.target.kafka.customdeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.kafka.model.User;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class UserDeSerializer implements Deserializer<User> {
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public User deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;
        try{
            user = objectMapper.readValue(data, User.class);
        }catch (IOException ex){
            System.out.println("Exception occurred while de-serializing: "+ex.getMessage());
        }

        return user;
    }

    public void close() {

    }
}
