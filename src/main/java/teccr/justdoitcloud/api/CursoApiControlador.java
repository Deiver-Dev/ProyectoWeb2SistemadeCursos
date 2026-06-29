package teccr.justdoitcloud.api;

import teccr.justdoitcloud.entidades.Categoria;
import teccr.justdoitcloud.entidades.Curso;
import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.servicios.CategoriaServicio;
import teccr.justdoitcloud.servicios.CursoServicio;
import teccr.justdoitcloud.servicios.UsuarioServicio;

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

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * GET /api/cursos
     *
     * Obtiene la lista de todos los cursos activos disponibles
     * en el catálogo de DigiTech Academy.
     *
     * Ejemplo:
     * GET http://localhost:8080/api/cursos
     */
    @GetMapping("/cursos")
    public ResponseEntity<List<Curso>> obtenerCursos() {
        return ResponseEntity.ok(cursoServicio.obtenerCursosActivos());
    }

    /**
     * GET /api/cursos/{id}
     *
     * Obtiene la información detallada de un curso utilizando
     * su identificador.
     *
     * Ejemplo:
     * GET http://localhost:8080/api/cursos/1
     */
    @GetMapping("/cursos/{id}")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        return cursoServicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/cursos
     *
     * Crea un nuevo curso a partir de la información recibida
     * en formato JSON.
     *
     * Parámetros requeridos:
     * - nombre
     * - descripcion
     * - creditos
     * - categoriaId
     * - profesorCorreo
     *
     * Ejemplo:
     * POST http://localhost:8080/api/cursos
     */
    @PostMapping("/cursos")
    public ResponseEntity<Curso> crearCurso(@RequestBody CursoRequest request) {

        try {

            Categoria categoria = categoriaServicio.buscarPorId(request.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

            Usuario profesor = usuarioServicio.buscarPorCorreo(request.profesorCorreo())
                    .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

            Curso curso = cursoServicio.crear(
                    request.nombre(),
                    request.descripcion(),
                    request.creditos(),
                    profesor,
                    categoria
            );

            return ResponseEntity.status(201).body(curso);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Clase utilizada para recibir el JSON enviado desde Postman
     * o cualquier otro cliente REST al crear un nuevo curso.
     */
    public record CursoRequest(
            String nombre,
            String descripcion,
            int creditos,
            Long categoriaId,
            String profesorCorreo
    ) {}
}
