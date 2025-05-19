package com.example.BarberCutzLeo.Reservas.repositorio;

import com.example.BarberCutzLeo.Reservas.model.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReservasRepo extends JpaRepository<Reservas, UUID> {

    List<Reservas> findByEstilistasIdAndFechaReserva(UUID estilistaId, LocalDateTime fechaReserva);

    @Query("SELECT r FROM Reservas r " +
            "WHERE r.estilistas.id = :estilistaId " +
            "AND r.fechaReserva < :fin " +
            "AND r.fechaReserva >= :inicioMenosDuracion")
    List<Reservas> buscarSolapadas(
            @Param("estilistaId") UUID estilistaId,
            @Param("inicioMenosDuracion") LocalDateTime inicioMenosDuracion,
            @Param("fin") LocalDateTime fin);


}
