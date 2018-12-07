package com.target.kafka.producer;

import com.target.kafka.model.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerDriver {

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeSerializer");
        properties.put("value.serializer", "com.target.kafka.customserializer.UserSerializer");

        KafkaProducer<String, User> kafkaProducer = new KafkaProducer<String, User>(properties);

        User user1 = new User(1, "Mahantesh", "Bangalore");

        try{
            kafkaProducer.send(new ProducerRecord<String, User>(
                    "my-topic", "User Object", user1));
        }catch(Exception ex){
            System.out.println("An exception occurred during message sending to topic: "+ex.getMessage());
        }finally {
            kafkaProducer.close();
        }
    }
}
