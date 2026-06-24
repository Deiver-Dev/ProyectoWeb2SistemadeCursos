package teccr.justdoitcloud.servicios;

import teccr.justdoitcloud.entidades.Inscripcion;
import teccr.justdoitcloud.entidades.Nota;
import teccr.justdoitcloud.repositorios.NotaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NotaServicio {

    @Autowired
    private NotaRepositorio notaRepositorio;

    // Registrar una nota para una inscripcion (solo Profesor)
    public Nota registrar(Inscripcion inscripcion, Double valor, String observacion) {
        if (notaRepositorio.existsByInscripcion(inscripcion)) {
            throw new RuntimeException("Esta inscripcion ya tiene una nota registrada");
        }
        if (valor < 0 || valor > 100) {
            throw new RuntimeException("La nota debe estar entre 0 y 100");
        }
        Nota nota = new Nota(inscripcion, valor, observacion);
        return notaRepositorio.save(nota);
    }

    // Actualizar una nota existente
    public Nota actualizar(Long notaId, Double nuevoValor, String nuevaObservacion) {
        Nota nota = notaRepositorio.findById(notaId)
            .orElseThrow(() -> new RuntimeException("Nota no encontrada"));
        if (nuevoValor < 0 || nuevoValor > 100) {
            throw new RuntimeException("La nota debe estar entre 0 y 100");
        }
        nota.setValor(nuevoValor);
        nota.setObservacion(nuevaObservacion);
        return notaRepositorio.save(nota);
    }

    // Buscar nota por inscripcion
    public Optional<Nota> buscarPorInscripcion(Inscripcion inscripcion) {
        return notaRepositorio.findByInscripcion(inscripcion);
    }

    // Buscar nota por ID
    public Optional<Nota> buscarPorId(Long id) {
        return notaRepositorio.findById(id);
    }
}
