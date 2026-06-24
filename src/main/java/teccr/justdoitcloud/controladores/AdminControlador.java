package teccr.justdoitcloud.controladores;

import teccr.justdoitcloud.entidades.Categoria;
import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.servicios.CategoriaServicio;
import teccr.justdoitcloud.servicios.CursoServicio;
import teccr.justdoitcloud.servicios.InscripcionServicio;
import teccr.justdoitcloud.servicios.NotaServicio;
import teccr.justdoitcloud.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired private CursoServicio cursoServicio;
    @Autowired private CategoriaServicio categoriaServicio;
    @Autowired private UsuarioServicio usuarioServicio;
    @Autowired private InscripcionServicio inscripcionServicio;
    @Autowired private NotaServicio notaServicio;

    // Panel principal del profesor
    @GetMapping
    public String panel(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        usuarioServicio.buscarPorCorreo(userDetails.getUsername()).ifPresent(u -> {
            model.addAttribute("cursos", cursoServicio.obtenerCursosPorProfesor(u));
            model.addAttribute("usuarioActual", u);
        });
        return "panel-admin";
    }

    // Formulario para crear curso
    @GetMapping("/cursos/nuevo")
    public String nuevoCurso(Model model) {
        model.addAttribute("categorias", categoriaServicio.obtenerTodas());
        return "form-curso";
    }

    // Guardar nuevo curso
    @PostMapping("/cursos/nuevo")
    public String guardarCurso(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam int creditos,
            @RequestParam Long categoriaId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Usuario profesor = usuarioServicio.buscarPorCorreo(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        Categoria categoria = categoriaServicio.buscarPorId(categoriaId)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        cursoServicio.crear(nombre, descripcion, creditos, profesor, categoria);
        return "redirect:/admin";
    }

    // Cambiar estado activo/inactivo de un curso
    @PostMapping("/cursos/{id}/estado")
    public String cambiarEstado(@PathVariable Long id, @RequestParam boolean activo) {
        cursoServicio.cambiarEstado(id, activo);
        return "redirect:/admin";
    }

    // Ver estudiantes inscritos en un curso
    @GetMapping("/cursos/{id}/estudiantes")
    public String verEstudiantes(@PathVariable Long id, Model model) {
        cursoServicio.buscarPorId(id).ifPresent(curso -> {
            model.addAttribute("curso", curso);
            model.addAttribute("inscripciones", inscripcionServicio.obtenerPorCurso(curso));
        });
        return "estudiantes-curso";
    }

    // Registrar nota a un estudiante
    @PostMapping("/notas/registrar")
    public String registrarNota(
            @RequestParam Long inscripcionId,
            @RequestParam Double valor,
            @RequestParam(required = false) String observacion,
            @RequestParam Long cursoId) {
        inscripcionServicio.buscarPorId(inscripcionId).ifPresent(inscripcion -> {
            try {
                notaServicio.registrar(inscripcion, valor, observacion);
            } catch (RuntimeException e) {
                notaServicio.buscarPorInscripcion(inscripcion).ifPresent(nota -> {
                    notaServicio.actualizar(nota.getId(), valor, observacion);
                });
            }
        });
        return "redirect:/admin/cursos/" + cursoId + "/estudiantes";
    }
}
