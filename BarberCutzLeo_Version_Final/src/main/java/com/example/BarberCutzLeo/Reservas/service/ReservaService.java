package com.example.BarberCutzLeo.Reservas.service;

import com.example.BarberCutzLeo.Estilistas.Estilistas;
import com.example.BarberCutzLeo.Estilistas.EstilistasRepo;
import com.example.BarberCutzLeo.Reservas.Dto.CrearReservaDto;
import com.example.BarberCutzLeo.Reservas.Dto.ReservaDto;
import com.example.BarberCutzLeo.Reservas.model.Reservas;
import com.example.BarberCutzLeo.Reservas.repositorio.ReservasRepo;
import com.example.BarberCutzLeo.Servicios.Servicios;
import com.example.BarberCutzLeo.Servicios.ServiciosRepo;
import com.example.BarberCutzLeo.user.dto.GetUsuario;
import com.example.BarberCutzLeo.user.model.Usuario;
import com.example.BarberCutzLeo.user.repositorio.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservasRepo reservasRepo;
    private final UsuarioRepo usuarioRepo;
    private final ServiciosRepo serviciosRepo;
    private final EstilistasRepo estilistasRepo;

    //metodo crear Reserva
    public ReservaDto crearReserva(UUID idServicio, UUID idEstilista, CrearReservaDto crearReservaDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String nombre = ((UserDetails) principal).getUsername();
            Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(nombre);
            if (usuario.isPresent()) {
                Optional<Servicios> servicios = serviciosRepo.findById(idServicio);
                Optional<Estilistas> estilistas = estilistasRepo.findById(idEstilista);
                if (estaDisponible(idEstilista,crearReservaDto.fecha())){
                    Reservas reservas = new Reservas();
                    reservas.setFechaReserva(crearReservaDto.fecha());
                    reservas.setUsuario(usuario.get());
                    reservas.setEstilistas(estilistas.get());
                    reservas.setServicios(servicios.get());
                    reservasRepo.save(reservas);
                    return  ReservaDto.of(reservas);
                }else {
                    throw new RuntimeException("a esa hora ya hay uan cita ");
                }



            } else {
            return null;
            }

        }
        return null;
    }

    public boolean estaDisponible(UUID idEstilista, LocalDateTime inicioReserva) {
        LocalDateTime finReserva = inicioReserva.plusMinutes(45);
        LocalDateTime inicioMenosDuracion = inicioReserva.minusMinutes(45);

        List<Reservas> solapadas = reservasRepo.buscarSolapadas(idEstilista, inicioMenosDuracion, finReserva);

        return solapadas.isEmpty();
    }
}
