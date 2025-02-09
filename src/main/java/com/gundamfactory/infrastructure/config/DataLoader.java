package com.gundamfactory.infrastructure.config;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.domain.entities.Gundam;
import com.gundamfactory.domain.entities.enums.GundamStatus;
import com.gundamfactory.domain.entities.enums.GundamType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(GundamRepositoryPort gundamRepositoryPort) {
        return args -> {
            gundamRepositoryPort.save(new Gundam(null, "RX-78-2 Gundam", "RX-78-2", "White/Blue/Red", 18.0, 60.0, "Beam Rifle", "Beam Saber", GundamType.MULTIPURPOSE, LocalDate.of(2079, 9, 18), GundamStatus.IN_SERVICE));
            gundamRepositoryPort.save(new Gundam(null, "ZGMF-X10A Freedom", "X10A", "Blue/White", 18.03, 71.5, "Plasma Beam Cannon", "Railgun", GundamType.SPACE, LocalDate.of(2082, 3, 15), GundamStatus.IN_SERVICE));
            gundamRepositoryPort.save(new Gundam(null, "MS-06 Zaku II", "MS-06", "Green", 17.5, 56.2, "Machine Gun", "Heat Hawk", GundamType.GROUND, LocalDate.of(2079, 4, 12), GundamStatus.RETIRED));
            gundamRepositoryPort.save(new Gundam(null, "GAT-X105 Strike", "X105", "White/Blue", 17.72, 64.8, "Beam Rifle", "Anti-Armor Knife", GundamType.MULTIPURPOSE, LocalDate.of(2081, 6, 8), GundamStatus.IN_SERVICE));
            gundamRepositoryPort.save(new Gundam(null, "ASW-G-08 Barbatos", "G-08", "White/Blue", 19.0, 37.4, "Mace", "Smoothbore Gun", GundamType.GROUND, LocalDate.of(2091, 10, 5), GundamStatus.UNDER_REPAIR));
            gundamRepositoryPort.save(new Gundam(null, "GN-001 Exia", "GN-001", "White/Blue", 18.3, 57.2, "GN Sword", "GN Beam Saber", GundamType.SPACE, LocalDate.of(2087, 1, 23), GundamStatus.IN_SERVICE));
            gundamRepositoryPort.save(new Gundam(null, "RX-0 Unicorn", "RX-0", "White", 19.7, 70.0, "Beam Magnum", "Beam Saber", GundamType.SPACE, LocalDate.of(2096, 2, 4), GundamStatus.IN_SERVICE));
            gundamRepositoryPort.save(new Gundam(null, "XXXG-00W0 Wing Zero", "00W0", "White/Blue/Red", 16.7, 8.0, "Twin Buster Rifle", "Beam Saber", GundamType.SPACE, LocalDate.of(2080, 12, 25), GundamStatus.DESTROYED));
            gundamRepositoryPort.save(new Gundam(null, "NZ-333 Alpha Azieru", "NZ-333", "Purple", 43.25, 294.8, "Mega Particle Cannon", "Funnels", GundamType.SPACE, LocalDate.of(2089, 11, 7), GundamStatus.RETIRED));
            gundamRepositoryPort.save(new Gundam(null, "RX-178 Gundam Mk-II", "RX-178", "Black/Yellow", 18.5, 33.4, "Beam Rifle", "Beam Saber", GundamType.GROUND, LocalDate.of(2085, 8, 14), GundamStatus.IN_SERVICE));
        };
    }

}
