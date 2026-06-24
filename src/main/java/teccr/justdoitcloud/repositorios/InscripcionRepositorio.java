package teccr.justdoitcloud.repositorios;

import teccr.justdoitcloud.entidades.Curso;
import teccr.justdoitcloud.entidades.Inscripcion;
import teccr.justdoitcloud.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepositorio extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByEstudiante(Usuario estudiante);
    List<Inscripcion> findByCurso(Curso curso);
    Optional<Inscripcion> findByEstudianteAndCurso(Usuario estudiante, Curso curso);
    boolean existsByEstudianteAndCurso(Usuario estudiante, Curso curso);
}
