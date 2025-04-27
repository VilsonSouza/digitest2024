package com.example.controller;

import com.example.DTO.ParkingDTO;
import com.example.model.*;
import com.example.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    private final ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    @Operation(summary = "Obter todos os estacionamentos")
    @ApiResponse(responseCode = "200", description = "Lista de estacionamentos")
    @GetMapping
    public List<Parking> getAll() {
        return service.listAll();
    }

    @Operation(
            summary = "Obter estacionamento por ID",
            description = "Recupera os detalhes de um estacionamento com base no ID fornecido.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estacionamento encontrado"),
                    @ApiResponse(responseCode = "404", description = "Estacionamento não encontrado")
            }
    )
    @GetMapping("/{id}")
    public Parking getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @Operation(summary = "Criar um novo estacionamento")
    @ApiResponse(responseCode = "200", description = "Estacionamento criado com sucesso")
    @PostMapping
    public Parking create(@RequestBody ParkingDTO dto) {
        Parking parking = new Parking();
        parking.setPlaca(dto.getPlaca());
        parking.setModelo(dto.getModelo());
        parking.setCor(dto.getCor());
        return service.create(parking);
    }

    @Operation(summary = "Atualizar um estacionamento")
    @ApiResponse(responseCode = "200", description = "Estacionamento atualizado")
    @PutMapping("/{id}")
    public Parking update(@PathVariable UUID id, @RequestBody ParkingDTO dto) {
        Parking parking = new Parking();
        parking.setPlaca(dto.getPlaca());
        parking.setModelo(dto.getModelo());
        parking.setCor(dto.getCor());
        return service.update(id, parking);
    }

    @Operation(summary = "Deletar um estacionamento")
    @ApiResponse(responseCode = "200", description = "Estacionamento deletado")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
