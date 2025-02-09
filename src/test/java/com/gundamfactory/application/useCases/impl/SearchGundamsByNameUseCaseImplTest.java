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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchGundamsByNameUseCaseImplTest {

    @Mock
    private GundamRepositoryPort gundamRepositoryPort;

    @InjectMocks
    private SearchGundamsByNameUseCaseImpl searchGundamsByNameUseCase;
    private Gundam gundam1;

    @BeforeEach
    void setUp() {
        gundam1 = Gundam.builder()
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
    void testSearchGundamsByNameFound() {

        String searchTerm = "RX";
        when(gundamRepositoryPort.findByNameContainingIgnoreCase(searchTerm)).thenReturn(List.of(gundam1));

        List<Gundam> result = searchGundamsByNameUseCase.apply(searchTerm);

        assertEquals(1, result.size());
        assertEquals("RX-78-2 Gundam", result.get(0).getName());

        verify(gundamRepositoryPort, times(1)).findByNameContainingIgnoreCase(searchTerm);
    }

    @Test
    void testSearchGundamsByNameNotFound() {

        String searchTerm = "Unicorn";
        when(gundamRepositoryPort.findByNameContainingIgnoreCase(searchTerm)).thenReturn(List.of());

        List<Gundam> result = searchGundamsByNameUseCase.apply(searchTerm);

        assertEquals(0, result.size());

        verify(gundamRepositoryPort, times(1)).findByNameContainingIgnoreCase(searchTerm);
    }
}
