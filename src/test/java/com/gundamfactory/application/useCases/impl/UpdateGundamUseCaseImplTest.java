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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateGundamUseCaseImplTest {

    @Mock
    private GundamRepositoryPort gundamRepositoryPort;

    @InjectMocks
    private UpdateGundamUseCaseImpl updateGundamUseCase;

    private Gundam existingGundam;
    private Gundam updatedGundam;

    @BeforeEach
    void setUp() {
        existingGundam = Gundam.builder()
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

        updatedGundam = Gundam.builder()
                .name("RX-78-2 Gundam Updated")
                .model("RX-78-3")
                .color("Black/Gold")
                .height(18.5)
                .weight(62.0)
                .primaryWeapon("Hyper Beam Rifle")
                .secondaryWeapon("Shield")
                .gundamType(GundamType.SPACE)
                .manufacturingDate(LocalDate.of(2080, 1, 1))
                .gundamStatus(GundamStatus.UNDER_REPAIR)
                .build();
    }

    @Test
    void testUpdateGundamSuccessfully() {

        when(gundamRepositoryPort.findById(1L)).thenReturn(Optional.of(existingGundam));
        when(gundamRepositoryPort.save(existingGundam)).thenReturn(existingGundam);

        Optional<Gundam> result = updateGundamUseCase.apply(1L, updatedGundam);

        assertTrue(result.isPresent());
        assertEquals("RX-78-2 Gundam Updated", result.get().getName());
        assertEquals("RX-78-3", result.get().getModel());
        assertEquals("Hyper Beam Rifle", result.get().getPrimaryWeapon());

        verify(gundamRepositoryPort, times(1)).findById(1L);
        verify(gundamRepositoryPort, times(1)).save(existingGundam);
    }

    @Test
    void testUpdateGundamNotFound() {

        when(gundamRepositoryPort.findById(2L)).thenReturn(Optional.empty());

        Optional<Gundam> result = updateGundamUseCase.apply(2L, updatedGundam);

        assertFalse(result.isPresent());

        verify(gundamRepositoryPort, times(1)).findById(2L);
        verify(gundamRepositoryPort, never()).save(any(Gundam.class));
    }
}
