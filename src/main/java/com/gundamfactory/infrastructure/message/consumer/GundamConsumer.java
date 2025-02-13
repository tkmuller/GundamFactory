package com.gundamfactory.infrastructure.message.consumer;

import com.gundamfactory.application.useCases.CreateGundamUseCase;
import com.gundamfactory.domain.entities.Gundam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GundamConsumer {

    private static final Logger logger = LoggerFactory.getLogger(GundamConsumer.class);

    private final CreateGundamUseCase createGundamUseCase;

    public GundamConsumer(CreateGundamUseCase createGundamUseCase) {
        this.createGundamUseCase = createGundamUseCase;
    }

    @KafkaListener(topics = "gundam-production", groupId = "gundam-factory")
    public void consumeGundam(Gundam gundam) {
        createGundamUseCase.apply(gundam);
        logger.info("Gundam producido: " + gundam.getName());
    }
}
