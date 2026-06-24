package teccr.justdoitcloud.servicios;

import teccr.justdoitcloud.entidades.Curso;
import teccr.justdoitcloud.entidades.Inscripcion;
import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.repositorios.InscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServicio {

    @Autowired
    private InscripcionRepositorio inscripcionRepositorio;

    // Inscribir un estudiante en un curso
    public Inscripcion inscribir(Usuario estudiante, Curso curso) {
        if (inscripcionRepositorio.existsByEstudianteAndCurso(estudiante, curso)) {
            throw new RuntimeException("El estudiante ya esta inscrito en este curso");
        }
        if (!curso.isActivo()) {
            throw new RuntimeException("No se puede inscribir en un curso inactivo");
        }
        Inscripcion inscripcion = new Inscripcion(estudiante, curso);
        return inscripcionRepositorio.save(inscripcion);
    }

    // Obtener inscripciones de un estudiante
    public List<Inscripcion> obtenerPorEstudiante(Usuario estudiante) {
        return inscripcionRepositorio.findByEstudiante(estudiante);
    }

    // Obtener inscripciones de un curso
    public List<Inscripcion> obtenerPorCurso(Curso curso) {
        return inscripcionRepositorio.findByCurso(curso);
    }

    // Buscar inscripcion especifica
    public Optional<Inscripcion> buscar(Usuario estudiante, Curso curso) {
        return inscripcionRepositorio.findByEstudianteAndCurso(estudiante, curso);
    }

    // Buscar por ID
    public Optional<Inscripcion> buscarPorId(Long id) {
        return inscripcionRepositorio.findById(id);
    }

    // Cancelar una inscripcion
    public void cancelar(Long inscripcionId) {
        Inscripcion inscripcion = inscripcionRepositorio.findById(inscripcionId)
            .orElseThrow(() -> new RuntimeException("Inscripcion no encontrada"));
        inscripcion.setEstado(Inscripcion.Estado.CANCELADA);
        inscripcionRepositorio.save(inscripcion);
    }

    // Verificar si un estudiante esta inscrito en un curso
    public boolean estaInscrito(Usuario estudiante, Curso curso) {
        return inscripcionRepositorio.existsByEstudianteAndCurso(estudiante, curso);
    }
}
