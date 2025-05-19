package com.example.BarberCutzLeo.Reservas.controller;

import com.example.BarberCutzLeo.Reservas.Dto.CrearReservaDto;
import com.example.BarberCutzLeo.Reservas.Dto.ReservaDto;
import com.example.BarberCutzLeo.Reservas.model.Reservas;
import com.example.BarberCutzLeo.Reservas.service.ReservaService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping("usuario/reservar/{idServicio}/{idEstilista}")
    public ResponseEntity<ReservaDto> crearReserva(@PathVariable UUID idServicio, @PathVariable UUID idEstilista, @RequestBody CrearReservaDto crearReservaDto){
        ReservaDto reservas = reservaService.crearReserva(idServicio, idEstilista,crearReservaDto);
        return  ResponseEntity.status(201).body(reservas);
    }
}
