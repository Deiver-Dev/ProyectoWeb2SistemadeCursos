package teccr.justdoitcloud.servicios;

import teccr.justdoitcloud.entidades.Categoria;
import teccr.justdoitcloud.entidades.Curso;
import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.repositorios.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CursoServicio {

    @Autowired
    private CursoRepositorio cursoRepositorio;

    // Obtener todos los cursos activos (catalogo para estudiantes)
    public List<Curso> obtenerCursosActivos() {
        return cursoRepositorio.findByActivoTrue();
    }

    // Obtener todos los cursos (para el panel admin)
    public List<Curso> obtenerTodos() {
        return cursoRepositorio.findAll();
    }

    // Obtener cursos de un profesor especifico
    public List<Curso> obtenerCursosPorProfesor(Usuario profesor) {
        return cursoRepositorio.findByProfesor(profesor);
    }

    // Buscar curso por ID
    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepositorio.findById(id);
    }

    // Buscar cursos por nombre
    public List<Curso> buscarPorNombre(String nombre) {
        return cursoRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    // Crear un nuevo curso
    public Curso crear(String nombre, String descripcion, int creditos, Usuario profesor, Categoria categoria) {
        Curso curso = new Curso(nombre, descripcion, creditos, profesor, categoria);
        return cursoRepositorio.save(curso);
    }

    // Actualizar un curso existente
    public Curso actualizar(Long id, String nombre, String descripcion, int creditos, Categoria categoria) {
        Curso curso = cursoRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setNombre(nombre);
        curso.setDescripcion(descripcion);
        curso.setCreditos(creditos);
        curso.setCategoria(categoria);
        return cursoRepositorio.save(curso);
    }

    // Activar o desactivar un curso
    public void cambiarEstado(Long id, boolean activo) {
        Curso curso = cursoRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setActivo(activo);
        cursoRepositorio.save(curso);
    }

    // Eliminar un curso
    public void eliminar(Long id) {
        cursoRepositorio.deleteById(id);
    }
}
