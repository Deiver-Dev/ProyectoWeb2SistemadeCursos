package teccr.justdoitcloud.controladores;

import teccr.justdoitcloud.entidades.Curso;
import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.servicios.CursoServicio;
import teccr.justdoitcloud.servicios.InscripcionServicio;
import teccr.justdoitcloud.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inscripciones")
public class InscripcionControlador {

    @Autowired private InscripcionServicio inscripcionServicio;
    @Autowired private CursoServicio cursoServicio;
    @Autowired private UsuarioServicio usuarioServicio;

    // Mis inscripciones
    @GetMapping
    public String misInscripciones(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        usuarioServicio.buscarPorCorreo(userDetails.getUsername()).ifPresent(u -> {
            model.addAttribute("inscripciones", inscripcionServicio.obtenerPorEstudiante(u));
            model.addAttribute("usuarioActual", u);
        });
        return "mis-inscripciones";
    }

    // Inscribirse en un curso
    @PostMapping("/inscribir/{cursoId}")
    public String inscribir(@PathVariable Long cursoId,
                           @AuthenticationPrincipal UserDetails userDetails,
                           Model model) {
        try {
            Usuario usuario = usuarioServicio.buscarPorCorreo(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            Curso curso = cursoServicio.buscarPorId(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
            inscripcionServicio.inscribir(usuario, curso);
            return "redirect:/inscripciones?exito=true";
        } catch (RuntimeException e) {
            return "redirect:/cursos/" + cursoId + "?error=" + e.getMessage();
        }
    }

    // Cancelar inscripcion
    @PostMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Long id) {
        inscripcionServicio.cancelar(id);
        return "redirect:/inscripciones";
    }
}
