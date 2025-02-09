package com.gundamfactory.infrastructure.repositories;

import com.gundamfactory.domain.entities.Gundam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GundamRepository extends JpaRepository<Gundam, Long> {

    Page<Gundam> findAll(Pageable pageable);

    List<Gundam> findByNameContainingIgnoreCase(String name);

}
