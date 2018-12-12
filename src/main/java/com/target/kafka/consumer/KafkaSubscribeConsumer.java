package com.target.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaSubscribeConsumer {

    public static void main(String[] args) {

        KafkaConsumer kafkaConsumer = createKafkaConsumer();
        List<String> topicList = new ArrayList<String>();
        topicList.add("my-topic");
        topicList.add("my-other-topic");

        kafkaConsumer.subscribe(topicList);

        try{
            while(true){
                ConsumerRecords<String, String> consumerRecords
                        = kafkaConsumer.poll(10);
                for(ConsumerRecord consumerRecord : consumerRecords) {
                    System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d," +"Key: %s, Value: %s",
                                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key(), consumerRecord.value()));
                }
            }
        }catch(Exception ex) {
            System.out.println("Exception occurred during Record Polling: "+ ex.getMessage());
        }finally {
            kafkaConsumer.close();
        }
    }

    public static KafkaConsumer<String, String> createKafkaConsumer() {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-consumer-group");

        return new KafkaConsumer<String, String>(properties);
    }
}
