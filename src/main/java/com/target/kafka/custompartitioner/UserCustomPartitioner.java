package com.target.kafka.custompartitioner;

import com.target.kafka.serivceimpl.UserServiceImpl;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class UserCustomPartitioner implements Partitioner {

    private UserServiceImpl userService;

    public void configure(Map<String, ?> configs) {

    }

    public int partition(String topic, Object key, byte[] keyBytes,
                         Object value, byte[] valueBytes, Cluster cluster) {
        int partition = 0;
        String userName = (String)key;
        Integer userId = userService.findUser(userName);
        if(userId != null){
            partition = userId;
        }

        return partition;
    }

    public void close() {

    }
}
