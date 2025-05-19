package com.example.BarberCutzLeo.Estilistas;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EstilistasController {

    private final EstilistasRepo estilistasRepo;

    @GetMapping("usuario/estilistas")
    public ResponseEntity<List<Estilistas>> listarEstilistas(){
        List<Estilistas> estilistas = estilistasRepo.findAll();
        return ResponseEntity.ok(estilistas);
    }
}
