package com.target.kafka.consumergroups;

import com.target.kafka.consumer.KafkaSubscribeConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;

public class KafkaConsumerGroupApp1 {

    public static void main(String[] args) {

        KafkaConsumer<String, String> kafkaConsumer = KafkaSubscribeConsumer.createKafkaConsumer();

        List<String> topicList = new ArrayList<String>();
        topicList.add("my-topic");

        kafkaConsumer.subscribe(topicList);

        try{
            while(true){
                ConsumerRecords<String, String> consumerRecords =
                        kafkaConsumer.poll(100);
                for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                    System.out.println(
                            String.format("Topic: %s, Partition: %d, Value: %s", consumerRecord.topic(),
                                    consumerRecord.partition(), consumerRecord.value().toUpperCase())
                    );
                }
            }
        }catch(Exception ex) {
            System.out.println("An exception occurred while polling in Consumer: "+ex.getMessage());
        }finally{
            kafkaConsumer.close();
        }
    }
}
