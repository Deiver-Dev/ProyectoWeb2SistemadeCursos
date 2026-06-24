package teccr.justdoitcloud.servicios;

import teccr.justdoitcloud.entidades.Categoria;
import teccr.justdoitcloud.repositorios.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicio {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    // Obtener todas las categorias
    public List<Categoria> obtenerTodas() {
        return categoriaRepositorio.findAll();
    }

    // Buscar por ID
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepositorio.findById(id);
    }

    // Crear nueva categoria
    public Categoria crear(String nombre, String descripcion) {
        if (categoriaRepositorio.existsByNombre(nombre)) {
            throw new RuntimeException("Ya existe una categoria con ese nombre");
        }
        return categoriaRepositorio.save(new Categoria(nombre, descripcion));
    }
}
