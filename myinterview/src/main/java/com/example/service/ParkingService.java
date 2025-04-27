package com.example.service;

import com.example.model.Parking;
import com.example.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingService {

    private final ParkingRepository repository;

    public ParkingService(ParkingRepository repository) {
        this.repository = repository;
    }

    public List<Parking> listAll() {
        return repository.findAll();
    }

    public Parking getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Parking create(Parking parking) {
        return repository.save(parking);
    }

    public Parking update(UUID id, Parking parking) {
        if (repository.existsById(id)) {
            parking.setId(id);
            return repository.save(parking);
        }
        return null;
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
