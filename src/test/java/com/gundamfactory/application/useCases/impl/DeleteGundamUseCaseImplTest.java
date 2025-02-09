package com.gundamfactory.application.useCases.impl;

import com.gundamfactory.application.port.GundamRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteGundamUseCaseImplTest {

    @Mock
    private GundamRepositoryPort gundamRepositoryPort;

    @InjectMocks
    private DeleteGundamUseCaseImpl deleteGundamUseCase;

    private Long gundamId;

    @BeforeEach
    void setUp() {
        gundamId = 1L;  // ID del Gundam que vamos a eliminar
    }

    @Test
    void testDeleteGundamSuccessfully() {

        deleteGundamUseCase.accept(gundamId);

        verify(gundamRepositoryPort, times(1)).deleteById(gundamId);
    }
}
