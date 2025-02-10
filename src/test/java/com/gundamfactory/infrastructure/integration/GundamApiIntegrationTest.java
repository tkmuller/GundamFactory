package com.gundamfactory.infrastructure.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.gundamfactory.domain.entities.Gundam;
import com.gundamfactory.domain.entities.enums.GundamStatus;
import com.gundamfactory.domain.entities.enums.GundamType;
import com.gundamfactory.infrastructure.config.security.JwtUtil;
import com.gundamfactory.infrastructure.message.consumer.GundamConsumer;
import com.gundamfactory.infrastructure.message.producer.GundamProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GundamApiIntegrationTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GundamProducer gundamProducer;

    @MockBean
    private GundamConsumer gundamConsumer;

    private String jwtToken;

    @BeforeEach
    void setUp() {
        jwtToken = jwtUtil.generateToken("username"); // Método para generar o mockear un JWT válido
    }

    @Test
    void testCrearGundam() throws Exception {
        Gundam newGundam = Gundam.builder()
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

        mockMvc.perform(post("/gundams")
                        .header("Authorization", "Bearer " + jwtToken)  // Incluimos el JWT
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newGundam)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())  // Verifica que el ID esté presente en la respuesta
                .andExpect(jsonPath("$.name").value("RX-78-2 Gundam"));  // Verifica que el nombre sea correcto
    }

    @Test
    void testObtenerGundam() throws Exception {

        String response = mockMvc.perform(post("/gundams")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Gundam.builder()
                                .name("MS-06 Zaku II")
                                .model("MS-06")
                                .color("Green")
                                .height(17.5)
                                .weight(56.0)
                                .primaryWeapon("Machine Gun")
                                .secondaryWeapon("Heat Hawk")
                                .gundamType(GundamType.GROUND)
                                .manufacturingDate(LocalDate.of(2079, 8, 12))
                                .gundamStatus(GundamStatus.IN_SERVICE)
                                .build())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Gundam createdGundam = objectMapper.readValue(response, Gundam.class);

        mockMvc.perform(get("/gundams/" + createdGundam.getId())
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("MS-06 Zaku II"))
                .andExpect(jsonPath("$.color").value("Green"));
    }

    @Test
    void testEliminarGundam() throws Exception {

        String response = mockMvc.perform(post("/gundams")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Gundam.builder()
                                .name("GN-001 Exia")
                                .model("GN-001")
                                .color("Blue/White")
                                .height(18.3)
                                .weight(57.2)
                                .primaryWeapon("GN Sword")
                                .secondaryWeapon("GN Beam Saber")
                                .gundamType(GundamType.SPACE)
                                .manufacturingDate(LocalDate.of(2307, 4, 15))
                                .gundamStatus(GundamStatus.IN_SERVICE)
                                .build())))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Gundam createdGundam = objectMapper.readValue(response, Gundam.class);

        mockMvc.perform(delete("/gundams/" + createdGundam.getId())
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/gundams/" + createdGundam.getId())
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}