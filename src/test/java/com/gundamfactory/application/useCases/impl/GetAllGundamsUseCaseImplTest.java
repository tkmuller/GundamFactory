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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllGundamsUseCaseImplTest {

    @Mock
    private GundamRepositoryPort gundamRepositoryPort;

    @InjectMocks
    private GetAllGundamsUseCaseImpl getAllGundamsUseCase;

    private List<Gundam> gundamList;

    @BeforeEach
    void setUp() {
        gundamList = List.of(
                Gundam.builder()
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
                        .build(),

                Gundam.builder()
                        .name("MS-06 Zaku II")
                        .model("MS-06")
                        .color("Green")
                        .height(17.5)
                        .weight(58.0)
                        .primaryWeapon("Zaku Machine Gun")
                        .secondaryWeapon("Heat Hawk")
                        .gundamType(GundamType.GROUND)
                        .manufacturingDate(LocalDate.of(2079, 8, 5))
                        .gundamStatus(GundamStatus.IN_SERVICE)
                        .build()
        );
    }

    @Test
    void testGetAllGundamsSuccessfully() {

        Pageable pageable = PageRequest.of(0, 5); // Página 0, 5 elementos por página
        Page<Gundam> gundamPage = new PageImpl<>(gundamList, pageable, gundamList.size());

        when(gundamRepositoryPort.findAll(pageable)).thenReturn(gundamPage);

        Page<Gundam> result = getAllGundamsUseCase.apply(pageable);

        assertEquals(2, result.getTotalElements());
        assertEquals("RX-78-2 Gundam", result.getContent().get(0).getName());
        assertEquals("MS-06 Zaku II", result.getContent().get(1).getName());

        verify(gundamRepositoryPort, times(1)).findAll(pageable);
    }
}
