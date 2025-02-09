package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.application.useCases.GetGundamByIdUseCase;
import com.gundamfactory.domain.entities.Gundam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

public class GetGundamByIdUseCaseImpl implements GetGundamByIdUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GetGundamByIdUseCaseImpl.class);

    private final GundamRepositoryPort gundamRepositoryPort;

    public GetGundamByIdUseCaseImpl(GundamRepositoryPort gundamRepositoryPort) {
        this.gundamRepositoryPort = gundamRepositoryPort;
    }

    @Override
    @Cacheable(value = "gundams", key = "#id")
    public Optional<Gundam> apply(Long id) {
        logger.warn("Buscando Gundam en la base de datos con ID: {}", id);
        return gundamRepositoryPort.findById(id);
    }
}
