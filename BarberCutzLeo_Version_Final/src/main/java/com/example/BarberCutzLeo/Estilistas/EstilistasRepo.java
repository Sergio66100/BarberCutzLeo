package com.example.BarberCutzLeo.Estilistas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstilistasRepo extends JpaRepository<Estilistas, UUID> {
}
