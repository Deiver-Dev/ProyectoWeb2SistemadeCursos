package teccr.justdoitcloud;

import teccr.justdoitcloud.entidades.RolUsuario;
import teccr.justdoitcloud.servicios.CategoriaServicio;
import teccr.justdoitcloud.servicios.CursoServicio;
import teccr.justdoitcloud.servicios.UsuarioServicio;
import teccr.justdoitcloud.entidades.Categoria;
import teccr.justdoitcloud.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CargadorDatos implements CommandLineRunner {

    @Autowired private UsuarioServicio usuarioServicio;
    @Autowired private CategoriaServicio categoriaServicio;
    @Autowired private CursoServicio cursoServicio;

    @Override
    public void run(String... args) throws Exception {

        // Crear usuarios de prueba
        Usuario profesor = usuarioServicio.registrar("Carlos Profesor", "profesor@tec.cr", "123456", RolUsuario.PROFESOR);
        usuarioServicio.registrar("Ana Estudiante", "estudiante@tec.cr", "123456", RolUsuario.ESTUDIANTE);
        usuarioServicio.registrar("Luis Estudiante", "luis@tec.cr", "123456", RolUsuario.ESTUDIANTE);

        // Crear categorias
        Categoria programacion = categoriaServicio.crear("Programacion", "Cursos de desarrollo de software");
        Categoria diseno = categoriaServicio.crear("Diseno", "Cursos de diseno grafico y UX");
        Categoria bases = categoriaServicio.crear("Bases de Datos", "Cursos de SQL y NoSQL");

        // Crear cursos
        cursoServicio.crear("Programacion Web con Spring", "Desarrollo de aplicaciones web usando Spring Boot", 4, profesor, programacion);
        cursoServicio.crear("Java Avanzado", "Patrones de diseno y arquitecturas en Java", 3, profesor, programacion);
        cursoServicio.crear("Diseno UX/UI", "Principios de experiencia de usuario", 3, profesor, diseno);
        cursoServicio.crear("SQL para Desarrolladores", "Consultas avanzadas y optimizacion", 2, profesor, bases);

        System.out.println("==> Datos de prueba cargados correctamente");
        System.out.println("==> Profesor: profesor@tec.cr / 123456");
        System.out.println("==> Estudiante: estudiante@tec.cr / 123456");
    }
}
