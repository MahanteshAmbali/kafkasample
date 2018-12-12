package com.target.kafka.consumergroups;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092, localhost:9093");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        try{
            for(int i = 1; i <= 100; i++){
                kafkaProducer.send(new ProducerRecord<String, String>(
                        "my-topic", "abcdefghijklmnopqrstuvwxyz"
                ));
            }
        }catch(Exception ex) {
            System.out.println("An exception occurred while posting message to topic: "+ex.getMessage());
        }finally{
            kafkaProducer.close();
        }
    }
}
