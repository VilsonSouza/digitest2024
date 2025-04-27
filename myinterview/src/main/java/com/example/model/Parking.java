package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Entity
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String placa;
    private String modelo;
    private String cor;
    private Instant horaEntrada;
    private Instant horaSaida;

    public Parking() {
        this.horaEntrada = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Instant getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Instant horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Instant getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Instant horaSaida) {
        this.horaSaida = horaSaida;
    }
}