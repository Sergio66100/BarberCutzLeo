package com.example.BarberCutzLeo.Servicios;

import com.example.BarberCutzLeo.Reservas.Dto.ReservaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServiciosController {
    private final ServiciosRepo serviciosRepo;

    @GetMapping("usuario/servicios")
    public ResponseEntity<List<Servicios>> listarServicios(){
        List<Servicios> servicios = serviciosRepo.findAll();
        return ResponseEntity.ok(servicios);
    }
}
