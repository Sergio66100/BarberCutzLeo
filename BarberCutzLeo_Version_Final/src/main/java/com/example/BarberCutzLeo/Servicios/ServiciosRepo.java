package com.example.BarberCutzLeo.Servicios;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiciosRepo extends JpaRepository<Servicios, UUID> {
}
