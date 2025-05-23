package com.example.BarberCutzLeo.user.service;

import com.example.BarberCutzLeo.Reservas.Dto.ReservaDto;
import com.example.BarberCutzLeo.Reservas.model.Reservas;
import com.example.BarberCutzLeo.Reservas.repositorio.ReservasRepo;
import com.example.BarberCutzLeo.user.dto.PostCrearUserDto;
import com.example.BarberCutzLeo.user.model.Administrador;
import com.example.BarberCutzLeo.user.model.UserRoles;
import com.example.BarberCutzLeo.user.model.Usuario;
import com.example.BarberCutzLeo.user.repositorio.AdministradorRepo;
import com.example.BarberCutzLeo.user.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministradorService {

    private final AdministradorRepo administradorRepo;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepo usuarioRepo;
    private final ReservasRepo reservasRepo;
    public Optional<Administrador> findById(UUID id){return administradorRepo.findById(id);}
    public Optional<Administrador> findByEmail(String email) {
        return administradorRepo.findFirstByEmail(email);
    }

    public Administrador crearAdministrador(PostCrearUserDto postCrearUserDto , EnumSet<UserRoles> userRoles){
        if (usuarioRepo.existsByEmailIgnoreCase(postCrearUserDto.email())||administradorRepo.existsByEmailIgnoreCase(postCrearUserDto.email())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"El email ya ha sido registrado");
        }
        Administrador administrador = Administrador.builder()
                .email(postCrearUserDto.email())
                .name(postCrearUserDto.name())
                .lastName(postCrearUserDto.lastName())
                .password(passwordEncoder.encode(postCrearUserDto.password()))
                .createdAt(LocalDateTime.now())
                .enabled(true)
                .birthDate(postCrearUserDto.nacimiento())
                .roles(EnumSet.of(UserRoles.ADMINISTRADOR))
                .build();
        return administradorRepo.save(administrador);
    }

    public Administrador createWithRole(PostCrearUserDto postCrearUserDto){
        return crearAdministrador(postCrearUserDto,EnumSet.of(UserRoles.ADMINISTRADOR));
    }
    public List<Usuario> usuariosRegistrados(){
        List<Usuario> usuarios = usuarioRepo.findByEnabledFalse();
        if (usuarios.isEmpty()){
            throw new RuntimeException("No se ha encontrados usuarios que se hayan registrados");
        }else {
            return usuarioRepo.findByEnabledFalse();
        }

    }

    public void setearEneable (String email){
        Optional<Usuario> usuario = usuarioRepo.findFirstByEmail(email);
        if (usuario.isPresent()){
            Usuario usuario1 = usuario.get();
            usuario1.setEnabled(true);
            usuarioRepo.save(usuario1);
        }else {
            throw new RuntimeException("Usuario con email: '"+email+"' no encontrado");
        }
    }

    public List<ReservaDto> verReservas(){
        List<Reservas> reservas= reservasRepo.findAll();
        List<ReservaDto> reservaDtos = reservas.stream().map(ReservaDto::of).collect(Collectors.toList());
        return reservaDtos;
    }
}
