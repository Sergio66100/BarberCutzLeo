package com.example.BarberCutzLeo.user.controller;

import com.example.BarberCutzLeo.Reservas.Dto.ReservaDto;
import com.example.BarberCutzLeo.security.jwt.JwtProvider;
import com.example.BarberCutzLeo.user.dto.JwtUserResponse;
import com.example.BarberCutzLeo.user.dto.PostCrearUserDto;
import com.example.BarberCutzLeo.user.dto.PostLogin;
import com.example.BarberCutzLeo.user.dto.PostRegistroDto;
import com.example.BarberCutzLeo.user.model.Administrador;
import com.example.BarberCutzLeo.user.model.Usuario;
import com.example.BarberCutzLeo.user.service.AdministradorService;
import com.example.BarberCutzLeo.user.service.UsuarioService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final AdministradorService administradorService;

    @PostMapping("auth/register/user")
    public ResponseEntity<?> crearUser(@RequestBody PostCrearUserDto postCrearUserDto) {
        try {
            Usuario usuario = usuarioService.createWithRole(postCrearUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Usuario(usuario));
        } catch (ResponseStatusException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getReason());
        }
    }


    @PostMapping("/auth/login/user")
    public ResponseEntity<JwtUserResponse> loginUser(@RequestBody PostLogin postLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        postLogin.email(),
                        postLogin.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.ofUsuario(usuario, token));
    }

    @GetMapping("adminsitrador/ver/reservas")
    public ResponseEntity<List<ReservaDto>> verReservas(){
        List<ReservaDto> reservaDtos = administradorService.verReservas();
        return ResponseEntity.ok(reservaDtos);
    }


    @PostMapping("auth/register/admin")
    public ResponseEntity<?> crearAdministrador(@RequestBody PostCrearUserDto postCrearUserDto) {
        try {
            Administrador administrador = administradorService.createWithRole(postCrearUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(PostRegistroDto.Administrador(administrador));
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("auth/login/admin")
    public ResponseEntity<JwtUserResponse> loginadmin(@RequestBody PostLogin postLogin){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        postLogin.email(),
                        postLogin.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Administrador administrador = (Administrador) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(JwtUserResponse.ofAdminsitrador(administrador, token));
    }

}