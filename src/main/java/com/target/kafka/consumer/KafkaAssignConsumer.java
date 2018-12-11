package com.target.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.List;

public class KafkaAssignConsumer {

    public static void main(String[] args) {

        KafkaConsumer<String, String> kafkaConsumer = KafkaSubscribeConsumer.createKafkaConsumer();

        TopicPartition topicPartition = new TopicPartition("my-topic", 0);
        TopicPartition topicPartition1 = new TopicPartition("my-other-topic", 1);

        List<TopicPartition> arrayListOfTopicPartitions = new ArrayList<TopicPartition>();
        arrayListOfTopicPartitions.add(topicPartition);
        arrayListOfTopicPartitions.add(topicPartition1);

        kafkaConsumer.assign(arrayListOfTopicPartitions);

        try{
            while(true){
                ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(10);
                for(ConsumerRecord consumerRecord : consumerRecords) {
                    System.out.println(String.format("Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s",
                            consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset(), consumerRecord.key(), consumerRecord.value()));
                }
            }
        }catch(Exception ex) {
            System.out.println("Exception occurred while polling for records: "+ex.getMessage());
        }finally {
            kafkaConsumer.close();
        }
    }
}
