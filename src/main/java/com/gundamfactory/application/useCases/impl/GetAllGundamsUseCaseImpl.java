package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.application.useCases.GetAllGundamsUseCase;
import com.gundamfactory.domain.entities.Gundam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetAllGundamsUseCaseImpl implements GetAllGundamsUseCase {

    private final GundamRepositoryPort gundamRepositoryPort;

    public GetAllGundamsUseCaseImpl(GundamRepositoryPort gundamRepositoryPort) {
        this.gundamRepositoryPort = gundamRepositoryPort;
    }

    @Override
    public Page<Gundam> apply(Pageable pageable) {
        return gundamRepositoryPort.findAll(pageable);
    }
}
