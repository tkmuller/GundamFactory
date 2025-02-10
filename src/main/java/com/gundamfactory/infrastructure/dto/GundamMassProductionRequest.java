package com.gundamfactory.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GundamMassProductionRequest {
    private CreateGundamRequest gundam;
    private int quantity;

}
