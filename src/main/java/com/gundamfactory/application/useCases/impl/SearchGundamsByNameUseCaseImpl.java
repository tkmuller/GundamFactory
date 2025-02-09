package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.application.useCases.SearchGundamsByNameUseCase;
import com.gundamfactory.domain.entities.Gundam;

import java.util.List;

public class SearchGundamsByNameUseCaseImpl implements SearchGundamsByNameUseCase {

    private final GundamRepositoryPort gundamRepositoryPort;

    public SearchGundamsByNameUseCaseImpl(GundamRepositoryPort gundamRepositoryPort) {
        this.gundamRepositoryPort = gundamRepositoryPort;
    }

    @Override
    public List<Gundam> apply(String name) {
        return gundamRepositoryPort.findByNameContainingIgnoreCase(name);
    }
}
