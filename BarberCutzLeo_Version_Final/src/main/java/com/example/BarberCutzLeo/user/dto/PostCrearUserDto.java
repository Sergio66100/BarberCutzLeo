package com.example.BarberCutzLeo.user.dto;

import java.time.LocalDate;

public record PostCrearUserDto(String email,
                               String name,
                               String lastName,
                               String password,
                               String fotoUrl,
                               String phoneNumber,
                               LocalDate nacimiento) {
}
