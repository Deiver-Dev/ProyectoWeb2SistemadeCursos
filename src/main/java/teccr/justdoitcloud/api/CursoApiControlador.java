package teccr.justdoitcloud.api;

import teccr.justdoitcloud.entidades.Curso;
import teccr.justdoitcloud.servicios.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Endpoints REST publicos
@RestController
@RequestMapping("/api")
public class CursoApiControlador {

    @Autowired
    private CursoServicio cursoServicio;

    // GET /api/cursos — obtener todos los cursos activos
    @GetMapping("/cursos")
    public ResponseEntity<List<Curso>> obtenerCursos() {
        return ResponseEntity.ok(cursoServicio.obtenerCursosActivos());
    }

    // GET /api/cursos/{id} — obtener un curso por ID
    @GetMapping("/cursos/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        return cursoServicio.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/cursos — crear un nuevo curso (sin seguridad)
    @PostMapping("/cursos")
    public ResponseEntity<Curso> crearCurso(@RequestBody CursoRequest request) {
        try {
            teccr.justdoitcloud.entidades.Categoria categoria = new teccr.justdoitcloud.entidades.Categoria();
            // Para la API simplificada usamos el servicio con datos minimos
            return ResponseEntity.status(201).body(
                cursoServicio.crear(request.nombre(), request.descripcion(), request.creditos(), null, null)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Record para recibir JSON en el POST
    public record CursoRequest(String nombre, String descripcion, int creditos) {}
}
