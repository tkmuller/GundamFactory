package com.gundamfactory.infrastructure.controllers;

import com.gundamfactory.application.useCases.CreateGundamUseCase;
import com.gundamfactory.application.useCases.DeleteGundamUseCase;
import com.gundamfactory.application.useCases.GetAllGundamsUseCase;
import com.gundamfactory.application.useCases.GetGundamByIdUseCase;
import com.gundamfactory.application.useCases.MassProduceGundamUseCase;
import com.gundamfactory.application.useCases.SearchGundamsByNameUseCase;
import com.gundamfactory.application.useCases.UpdateGundamUseCase;
import com.gundamfactory.domain.entities.Gundam;
import com.gundamfactory.infrastructure.dto.CreateGundamRequest;
import com.gundamfactory.infrastructure.dto.GundamMassProductionRequest;
import com.gundamfactory.infrastructure.dto.PagedResponse;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/gundams")
public class GundamController {

    @Autowired
    private CreateGundamUseCase createGundamUseCase;

    @Autowired
    private GetAllGundamsUseCase getAllGundamsUseCase;

    @Autowired
    private GetGundamByIdUseCase getGundamByIdUseCase;

    @Autowired
    private UpdateGundamUseCase updateGundamUseCase;

    @Autowired
    private DeleteGundamUseCase deleteGundamUseCase;

    @Autowired
    private MassProduceGundamUseCase massProduceGundamUseCase;

    @Autowired
    private SearchGundamsByNameUseCase searchGundamsByNameUseCase;

    @GetMapping("/paged")
    public PagedResponse<Gundam> getAllGundamsPaged(@RequestParam(defaultValue = "1") @Min(1) int page,
                                                    @RequestParam(defaultValue = "5") @Min(1) int size) {
        Page<Gundam> gundamPage = getAllGundamsUseCase.apply(PageRequest.of(page - 1, size));

        return PagedResponse.<Gundam>builder()
                .content(gundamPage.getContent())
                .pageNumber(gundamPage.getNumber())
                .pageSize(gundamPage.getSize())
                .totalElements(gundamPage.getTotalElements())
                .totalPages(gundamPage.getTotalPages())
                .isLast(gundamPage.isLast())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gundam> getGundamById(@PathVariable Long id) {

        return getGundamByIdUseCase.apply(id)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/search")
    public List<Gundam> searchGundamsByName(@RequestParam String name) {
        return searchGundamsByNameUseCase.apply(name);
    }

    @PostMapping
    public Gundam createGundam(@RequestBody CreateGundamRequest request) {

        Gundam newGundam = Gundam.builder()
                .name(request.getName())
                .model(request.getModel())
                .color(request.getColor())
                .height(request.getHeight())
                .weight(request.getWeight())
                .primaryWeapon(request.getPrimaryWeapon())
                .secondaryWeapon(request.getSecondaryWeapon())
                .gundamType(request.getGundamType())
                .manufacturingDate(request.getManufacturingDate())
                .gundamStatus(request.getGundamStatus())
                .build();

        return createGundamUseCase.apply(newGundam);

    }

    @PostMapping("/mass-produce")
    public String massProduceGundams(@RequestBody GundamMassProductionRequest request) {

        Gundam newGundam = Gundam.builder()
                .name(request.getGundam().getName())
                .model(request.getGundam().getModel())
                .color(request.getGundam().getColor())
                .height(request.getGundam().getHeight())
                .weight(request.getGundam().getWeight())
                .primaryWeapon(request.getGundam().getPrimaryWeapon())
                .secondaryWeapon(request.getGundam().getSecondaryWeapon())
                .gundamType(request.getGundam().getGundamType())
                .manufacturingDate(request.getGundam().getManufacturingDate())
                .gundamStatus(request.getGundam().getGundamStatus())
                .build();

        massProduceGundamUseCase.accept(newGundam, request.getQuantity());
        return "Producción en masa iniciada para " + request.getQuantity() + " unidades del Gundam: " + request.getGundam().getName();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gundam> updateGundam(@PathVariable Long id, @RequestBody CreateGundamRequest request) {

        Gundam updatedGundam = Gundam.builder()
                .name(request.getName())
                .model(request.getModel())
                .color(request.getColor())
                .height(request.getHeight())
                .weight(request.getWeight())
                .primaryWeapon(request.getPrimaryWeapon())
                .secondaryWeapon(request.getSecondaryWeapon())
                .gundamType(request.getGundamType())
                .manufacturingDate(request.getManufacturingDate())
                .gundamStatus(request.getGundamStatus())
                .build();

        return updateGundamUseCase.apply(id, updatedGundam)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGundam(@PathVariable Long id) {

        deleteGundamUseCase.accept(id);

        return ResponseEntity.noContent().build();
    }
}
