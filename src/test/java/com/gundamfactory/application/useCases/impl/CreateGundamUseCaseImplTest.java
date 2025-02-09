package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import com.gundamfactory.domain.entities.Gundam;
import com.gundamfactory.domain.entities.enums.GundamStatus;
import com.gundamfactory.domain.entities.enums.GundamType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateGundamUseCaseImplTest {

    @Mock
    private GundamRepositoryPort gundamRepositoryPort;

    @InjectMocks
    private CreateGundamUseCaseImpl createGundamUseCase;

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
    void testCreateGundamSuccessfully() {

        when(gundamRepositoryPort.save(any(Gundam.class))).thenReturn(gundam);

        Gundam createdGundam = createGundamUseCase.apply(gundam);

        assertNotNull(createdGundam);
        assertEquals("RX-78-2 Gundam", createdGundam.getName());
        assertEquals("RX-78-2", createdGundam.getModel());
        assertEquals("Beam Rifle", createdGundam.getPrimaryWeapon());

        verify(gundamRepositoryPort, times(1)).save(gundam);
    }

    @Test
    void testCreateGundamWithArgumentCaptor() {

        when(gundamRepositoryPort.save(any(Gundam.class))).thenReturn(gundam);

        createGundamUseCase.apply(gundam);

        ArgumentCaptor<Gundam> captor = ArgumentCaptor.forClass(Gundam.class);
        verify(gundamRepositoryPort).save(captor.capture());

        Gundam capturedGundam = captor.getValue();
        assertEquals("RX-78-2 Gundam", capturedGundam.getName());
        assertEquals(18.0, capturedGundam.getHeight());
    }
}
