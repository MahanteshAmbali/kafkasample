package com.target.kafka.customserializer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.kafka.model.User;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerializer implements Serializer<User> {

    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    public byte[] serialize(String topic, User user) {
        byte[] byteArray = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            byteArray = objectMapper.writeValueAsString(user).getBytes();
        }catch(JsonProcessingException ex){
            System.out.println("JSON Processing Exception Occurred: "+ex.getMessage());
        }

        return byteArray;
    }

    public void close() {

    }
}
