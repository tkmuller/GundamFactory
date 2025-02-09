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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetGundamByIdUseCaseImplTest {

    @Mock
    private GundamRepositoryPort gundamRepositoryPort;

    @InjectMocks
    private GetGundamByIdUseCaseImpl getGundamByIdUseCase;

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
    void testGetGundamByIdWhenExists() {

        when(gundamRepositoryPort.findById(1L)).thenReturn(Optional.of(gundam));

        Optional<Gundam> result = getGundamByIdUseCase.apply(1L);

        assertTrue(result.isPresent());
        assertEquals("RX-78-2 Gundam", result.get().getName());

        verify(gundamRepositoryPort, times(1)).findById(1L);
    }

    @Test
    void testGetGundamByIdWhenNotExists() {

        when(gundamRepositoryPort.findById(2L)).thenReturn(Optional.empty());

        Optional<Gundam> result = getGundamByIdUseCase.apply(2L);

        assertFalse(result.isPresent());

        verify(gundamRepositoryPort, times(1)).findById(2L);
    }
}
