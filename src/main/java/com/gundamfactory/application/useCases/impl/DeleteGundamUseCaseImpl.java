package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.application.useCases.DeleteGundamUseCase;
import org.springframework.cache.annotation.CacheEvict;

public class DeleteGundamUseCaseImpl implements DeleteGundamUseCase {

    private final GundamRepositoryPort gundamRepositoryPort;

    public DeleteGundamUseCaseImpl(GundamRepositoryPort gundamRepositoryPort) {
        this.gundamRepositoryPort = gundamRepositoryPort;
    }

    @Override
    @CacheEvict(value = "gundams", key = "#id")
    public void accept(Long id) {
        gundamRepositoryPort.deleteById(id);
    }
}
