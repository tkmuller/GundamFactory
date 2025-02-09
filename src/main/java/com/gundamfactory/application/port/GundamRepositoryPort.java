package com.gundamfactory.application.port;

import com.gundamfactory.domain.entities.Gundam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GundamRepositoryPort {
    Page<Gundam> findAll(Pageable pageable);

    Optional<Gundam> findById(Long id);

    Gundam save(Gundam gundam);

    void deleteById(Long id);

    List<Gundam> findByNameContainingIgnoreCase(String name);

    void produceGundam(Gundam gundam);
}
