package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.application.useCases.UpdateGundamUseCase;
import com.gundamfactory.domain.entities.Gundam;
import org.springframework.cache.annotation.CacheEvict;

import java.util.Optional;

public class UpdateGundamUseCaseImpl implements UpdateGundamUseCase {

    private final GundamRepositoryPort gundamRepositoryPort;

    public UpdateGundamUseCaseImpl(GundamRepositoryPort gundamRepositoryPort) {
        this.gundamRepositoryPort = gundamRepositoryPort;
    }

    @Override
    @CacheEvict(value = "gundams", key = "#id")
    public Optional<Gundam> apply(Long id, Gundam updatedGundam) {
        return gundamRepositoryPort.findById(id).map(existingGundam -> {
            existingGundam.updateFrom(updatedGundam);
            return gundamRepositoryPort.save(existingGundam);
        });

    }
}
