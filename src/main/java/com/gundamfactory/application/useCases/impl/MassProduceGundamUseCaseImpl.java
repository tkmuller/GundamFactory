package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.application.useCases.MassProduceGundamUseCase;
import com.gundamfactory.domain.entities.Gundam;

import java.util.Collections;

public class MassProduceGundamUseCaseImpl implements MassProduceGundamUseCase {

    private final GundamRepositoryPort gundamRepositoryPort;

    public MassProduceGundamUseCaseImpl(GundamRepositoryPort gundamRepositoryPort) {
        this.gundamRepositoryPort = gundamRepositoryPort;
    }

    @Override
    public void accept(Gundam gundam, Integer quantity) {

        Collections.nCopies(quantity, gundam)
                .forEach(gundamRepositoryPort::produceGundam);

    }
}
