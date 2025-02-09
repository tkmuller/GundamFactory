package com.gundamfactory.domain.entities;

import com.gundamfactory.domain.entities.enums.GundamStatus;
import com.gundamfactory.domain.entities.enums.GundamType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Builder
@Getter
public class Gundam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String model;
    private String color;
    private double height; // in meters
    private double weight; // in tons
    private String primaryWeapon;
    private String secondaryWeapon;

    @Enumerated(EnumType.STRING)
    private GundamType gundamType;

    private LocalDate manufacturingDate;

    @Enumerated(EnumType.STRING)
    private GundamStatus gundamStatus;

    public Gundam(Long id, String name, String model, String color, double height, double weight,
                  String primaryWeapon, String secondaryWeapon, GundamType gundamType,
                  LocalDate manufacturingDate, GundamStatus gundamStatus) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.color = color;
        this.height = height;
        this.weight = weight;
        this.primaryWeapon = primaryWeapon;
        this.secondaryWeapon = secondaryWeapon;
        this.gundamType = gundamType;
        this.manufacturingDate = manufacturingDate;
        this.gundamStatus = gundamStatus;
    }

    public Gundam() {
    }

    public void updateFrom(Gundam newGundam) {

        this.name = newGundam.getName();
        this.model = newGundam.getModel();
        this.color = newGundam.getColor();
        this.height = newGundam.getHeight();
        this.weight = newGundam.getWeight();
        this.primaryWeapon = newGundam.getPrimaryWeapon();
        this.secondaryWeapon = newGundam.getSecondaryWeapon();
        this.gundamType = newGundam.getGundamType();
        this.manufacturingDate = newGundam.getManufacturingDate();
        this.gundamStatus = newGundam.getGundamStatus();
    }

}
