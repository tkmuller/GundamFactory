package com.gundamfactory.infrastructure.dto;

import com.gundamfactory.domain.entities.Gundam;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GundamMassProductionRequest {
    private Gundam gundam;
    private int quantity;

}
