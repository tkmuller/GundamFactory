package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.application.useCases.CreateGundamUseCase;
import com.gundamfactory.domain.entities.Gundam;

public class CreateGundamUseCaseImpl implements CreateGundamUseCase {

    private final GundamRepositoryPort gundamRepositoryPort;

    public CreateGundamUseCaseImpl(GundamRepositoryPort gundamRepositoryPort) {
        this.gundamRepositoryPort = gundamRepositoryPort;
    }

    @Override
    public Gundam apply(Gundam gundam) {
        return gundamRepositoryPort.save(gundam);
    }
}
