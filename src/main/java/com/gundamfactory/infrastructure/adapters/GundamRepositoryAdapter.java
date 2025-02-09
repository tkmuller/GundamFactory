package com.gundamfactory.infrastructure.adapters;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.domain.entities.Gundam;
import com.gundamfactory.infrastructure.message.producer.GundamProducer;
import com.gundamfactory.infrastructure.repositories.GundamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GundamRepositoryAdapter implements GundamRepositoryPort {

    @Autowired
    private GundamRepository gundamRepository;

    @Autowired
    private GundamProducer gundamProducer;

    @Override
    public Page<Gundam> findAll(Pageable pageable) {
        return gundamRepository.findAll(pageable);
    }

    @Override
    public Optional<Gundam> findById(Long id) {
        return gundamRepository.findById(id);
    }

    @Override
    public Gundam save(Gundam gundam) {
        return gundamRepository.save(gundam);
    }

    @Override
    public void deleteById(Long id) {
        gundamRepository.deleteById(id);
    }

    @Override
    public List<Gundam> findByNameContainingIgnoreCase(String name) {
        return gundamRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public void produceGundam(Gundam gundam) {
        gundamProducer.produceGundam(gundam);
    }
}
