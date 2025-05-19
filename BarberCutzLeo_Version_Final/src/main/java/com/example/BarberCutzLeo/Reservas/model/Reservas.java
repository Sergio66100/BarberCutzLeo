package com.example.BarberCutzLeo.Reservas.model;


import com.example.BarberCutzLeo.Estilistas.Estilistas;
import com.example.BarberCutzLeo.Servicios.Servicios;
import com.example.BarberCutzLeo.user.model.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
// auto se te generen los constructures
@AllArgsConstructor
//para un constructor vacio
@NoArgsConstructor
//getter y setter
@Data

@Builder
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime fechaReserva;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Servicios servicios;

    @ManyToOne
    private Estilistas estilistas;








}
