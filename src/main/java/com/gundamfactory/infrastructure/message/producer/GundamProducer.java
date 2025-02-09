package com.gundamfactory.infrastructure.message.producer;

import com.gundamfactory.domain.entities.Gundam;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class GundamProducer {

    private final KafkaTemplate<String, Gundam> kafkaTemplate;

    public GundamProducer(KafkaTemplate<String, Gundam> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceGundam(Gundam gundam) {
        kafkaTemplate.send("gundam-production", gundam);
    }
}
