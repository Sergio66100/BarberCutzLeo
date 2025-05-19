package com.example.BarberCutzLeo.user.service;

import com.example.BarberCutzLeo.user.dto.GetUsuario;
import com.example.BarberCutzLeo.user.dto.PostCrearUserDto;
import com.example.BarberCutzLeo.user.dto.PostLogin;
import com.example.BarberCutzLeo.user.model.UserRoles;
import com.example.BarberCutzLeo.user.model.Usuario;
import com.example.BarberCutzLeo.user.repositorio.AdministradorRepo;
import com.example.BarberCutzLeo.user.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepo usuarioRepo;
    private final PasswordEncoder passwordEncoder;
    private final AdministradorRepo administradorRepo;


    public Optional<Usuario> findById(UUID id){return usuarioRepo.findById(id);}

    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepo.findFirstByEmail(email);
    }

    public  Usuario crearUsuario(PostCrearUserDto postCrearUserDto, EnumSet<UserRoles> userRoles){
        if (usuarioRepo.existsByEmailIgnoreCase(postCrearUserDto.email())||administradorRepo.existsByEmailIgnoreCase(postCrearUserDto.email())){
            throw new RuntimeException("El email ya ha sido registrado");
        }

        Usuario usuario = Usuario.builder()
                .email(postCrearUserDto.email())
                .name(postCrearUserDto.name())
                .lastName(postCrearUserDto.lastName())
                .password(passwordEncoder.encode(postCrearUserDto.password()))
                .createdAt(LocalDateTime.now())
                .username(postCrearUserDto.name()+postCrearUserDto.lastName())
                .phoneNumber(postCrearUserDto.phoneNumber())
                .birthDate(postCrearUserDto.nacimiento())
                .roles(EnumSet.of(UserRoles.USER))
                .enabled(true)
                .build();

        return usuarioRepo.save(usuario);

    }

    public Usuario createWithRole(PostCrearUserDto postCrearUserDto){
        return crearUsuario(postCrearUserDto,EnumSet.of(UserRoles.USER));
    }
    public GetUsuario getUsuario(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre = ((UserDetails) principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);

            // Verificar si el usuario existe
            if (usuario.isPresent()) {
                return GetUsuario.of(usuario.get());


            } else {
                System.out.println("Usuario no encontrado.");
            }
        }
        return null;

    }
    public Usuario setearEnabled(PostLogin postCrearUserDto){
        Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(postCrearUserDto.email());

        if (usuario.isPresent() || usuario.get().isEnabled()){
            usuario.get().setEnabled(true);
            return usuarioRepo.save(usuario.get());
        }else {
            throw new RuntimeException("No se encuentra el usuario");
        }
    }


}
