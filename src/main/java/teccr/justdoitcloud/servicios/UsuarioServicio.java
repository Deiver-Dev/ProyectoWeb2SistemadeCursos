package teccr.justdoitcloud.servicios;

import teccr.justdoitcloud.entidades.RolUsuario;
import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrar un nuevo usuario (estudiante por defecto)
    public Usuario registrar(String nombre, String correo, String contrasena, RolUsuario rol) {
        if (usuarioRepositorio.existsByCorreo(correo)) {
            throw new RuntimeException("Ya existe un usuario con ese correo");
        }
        String contrasenaCifrada = passwordEncoder.encode(contrasena);
        Usuario usuario = new Usuario(nombre, correo, contrasenaCifrada, rol);
        return usuarioRepositorio.save(usuario);
    }

    // Buscar usuario por correo
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepositorio.findByCorreo(correo);
    }

    // Obtener todos los estudiantes
    public List<Usuario> obtenerEstudiantes() {
        return usuarioRepositorio.findByRol(RolUsuario.ESTUDIANTE);
    }

    // Obtener todos los profesores
    public List<Usuario> obtenerProfesores() {
        return usuarioRepositorio.findByRol(RolUsuario.PROFESOR);
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepositorio.findAll();
    }

    // Verificar si un correo ya esta registrado
    public boolean correoExiste(String correo) {
        return usuarioRepositorio.existsByCorreo(correo);
    }
}
