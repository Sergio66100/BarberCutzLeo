package com.example.BarberCutzLeo.Reservas.Dto;

import com.example.BarberCutzLeo.Reservas.model.Reservas;
import com.example.BarberCutzLeo.user.model.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservaDto(UUID id,
                         String usuario,
                         String estilista,
                         String servicio,
                         LocalDateTime fecha) {
    public static ReservaDto of(Reservas r){
        return new ReservaDto(
                r.getId(),
                r.getUsuario().getName(),
                r.getEstilistas().getNombre(),
                r.getServicios().getTipo(),
                r.getFechaReserva()
        );
    }
}
