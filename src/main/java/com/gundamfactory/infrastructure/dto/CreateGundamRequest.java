package com.gundamfactory.infrastructure.dto;

import com.gundamfactory.domain.entities.enums.GundamStatus;
import com.gundamfactory.domain.entities.enums.GundamType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class CreateGundamRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El modelo es obligatorio")
    private String model;

    @NotBlank(message = "El color es obligatorio")
    private String color;

    @Positive(message = "La altura debe ser un número positivo")
    private double height;

    @Positive(message = "El peso debe ser un número positivo")
    private double weight;

    @NotBlank(message = "El arma principal es obligatoria")
    private String primaryWeapon;

    private String secondaryWeapon;

    @NotNull(message = "El tipo de Gundam es obligatorio")
    private GundamType gundamType;

    @NotNull(message = "La fecha de fabricación es obligatoria")
    private LocalDate manufacturingDate;

    @NotNull(message = "El estado del Gundam es obligatorio")
    private GundamStatus gundamStatus;

}
