package teccr.justdoitcloud.repositorios;

import teccr.justdoitcloud.entidades.Inscripcion;
import teccr.justdoitcloud.entidades.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NotaRepositorio extends JpaRepository<Nota, Long> {
    Optional<Nota> findByInscripcion(Inscripcion inscripcion);
    boolean existsByInscripcion(Inscripcion inscripcion);
}
