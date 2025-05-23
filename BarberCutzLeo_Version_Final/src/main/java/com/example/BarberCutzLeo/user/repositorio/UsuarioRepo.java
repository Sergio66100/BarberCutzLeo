package com.example.BarberCutzLeo.user.repositorio;

import com.example.BarberCutzLeo.user.dto.GetUsuario;
import com.example.BarberCutzLeo.user.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepo extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findFirstByEmail(String email);
    Usuario findByEmail(String email);
    boolean existsByEmailIgnoreCase(String email);
    List<Usuario> findByEnabledFalse();
    Optional<Usuario> findByEmailIgnoreCase(String nombre);
    List<Usuario> findByEnabledTrue();

    @Query("""
            select new com.example.BarberCutzLeo.user.dto.GetUsuario(
            u.id,
            u.username,
              u.name,
              u.lastName,
              u.phoneNumber,
              u.fotoUrl
            
            )
            from Usuario u
            where u.id = ?1
            """)
    GetUsuario getUsuario(UUID uuid);


}
