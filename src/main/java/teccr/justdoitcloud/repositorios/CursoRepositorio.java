package teccr.justdoitcloud.repositorios;

import teccr.justdoitcloud.entidades.Curso;
import teccr.justdoitcloud.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CursoRepositorio extends JpaRepository<Curso, Long> {
    List<Curso> findByActivoTrue();
    List<Curso> findByProfesor(Usuario profesor);
    List<Curso> findByNombreContainingIgnoreCase(String nombre);
}
