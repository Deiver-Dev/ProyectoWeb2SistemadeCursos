package teccr.justdoitcloud.repositorios;

import teccr.justdoitcloud.entidades.RolUsuario;
import teccr.justdoitcloud.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findByRol(RolUsuario rol);
    boolean existsByCorreo(String correo);
}
