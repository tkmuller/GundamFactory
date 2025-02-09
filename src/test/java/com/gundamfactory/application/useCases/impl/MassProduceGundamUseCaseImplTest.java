package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.domain.entities.Gundam;
import com.gundamfactory.domain.entities.enums.GundamStatus;
import com.gundamfactory.domain.entities.enums.GundamType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MassProduceGundamUseCaseImplTest {

    @Mock
    private GundamRepositoryPort gundamRepositoryPort;

    @InjectMocks
    private MassProduceGundamUseCaseImpl massProduceGundamUseCase;

    private Gundam gundam;

    @BeforeEach
    void setUp() {
        gundam = Gundam.builder()
                .name("RX-78-2 Gundam")
                .model("RX-78-2")
                .color("White/Blue/Red")
                .height(18.0)
                .weight(60.0)
                .primaryWeapon("Beam Rifle")
                .secondaryWeapon("Beam Saber")
                .gundamType(GundamType.MULTIPURPOSE)
                .manufacturingDate(LocalDate.of(2079, 9, 18))
                .gundamStatus(GundamStatus.IN_SERVICE)
                .build();
    }

    @Test
    void testMassProduceGundamsSuccessfully() {

        int quantity = 5;
        massProduceGundamUseCase.accept(gundam, quantity);

        verify(gundamRepositoryPort, times(quantity)).produceGundam(gundam);
    }

    @Test
    void testMassProduceGundamsWithZeroQuantity() {

        int quantity = 0;
        massProduceGundamUseCase.accept(gundam, quantity);

        verify(gundamRepositoryPort, times(0)).produceGundam(any(Gundam.class));
    }

    @Test
    void testMassProduceGundamsWithOneQuantity() {

        int quantity = 1;

        massProduceGundamUseCase.accept(gundam, quantity);

        verify(gundamRepositoryPort, times(quantity)).produceGundam(gundam);
    }
}
