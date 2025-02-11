package com.gundamfactory.infrastructure.dto;

import lombok.Builder;
import lombok.Getter;
import io.swagger.v3.oas.annotations.media.Schema;

@Builder
@Getter
public class GundamMassProductionRequest {
    private CreateGundamRequest gundam;

    @Schema(defaultValue = "10")
    private int quantity;

}
