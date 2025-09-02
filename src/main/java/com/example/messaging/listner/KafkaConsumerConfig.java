package com.example.messaging.listner;


import com.example.messaging.dto.ForgetPasswordRequest;
import com.example.messaging.dto.LoginNotificationDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, ForgetPasswordRequest> passwordResetRequestProducerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "new-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MessageSerializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES,"com.example.messaging.listner");
        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ForgetPasswordRequest> passwordNotificationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ForgetPasswordRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(passwordResetRequestProducerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, LoginNotificationDto> loginNotificationConsumerFactory(){
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "new-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, NotificationSerializer.class);
        return new DefaultKafkaConsumerFactory<>(config,
                new StringDeserializer(),
                new NotificationSerializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LoginNotificationDto> loginNotificationKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, LoginNotificationDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(loginNotificationConsumerFactory());
        return factory;
    }
}
