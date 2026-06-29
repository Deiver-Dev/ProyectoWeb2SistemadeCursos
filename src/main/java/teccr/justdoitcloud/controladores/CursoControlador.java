package teccr.justdoitcloud.controladores;

import teccr.justdoitcloud.entidades.Categoria;
import teccr.justdoitcloud.entidades.Curso;
import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.servicios.CategoriaServicio;
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
@RequestMapping("/cursos")
public class CursoControlador {

    @Autowired private CursoServicio cursoServicio;
    @Autowired private CategoriaServicio categoriaServicio;
    @Autowired private UsuarioServicio usuarioServicio;
    @Autowired private InscripcionServicio inscripcionServicio;

    // Catalogo de cursos activos
    @GetMapping
    public String catalogo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("cursos", cursoServicio.obtenerCursosActivos());
        if (userDetails != null) {
            usuarioServicio.buscarPorCorreo(userDetails.getUsername()).ifPresent(u -> {
                model.addAttribute("usuarioActual", u);
            });
        }
        return "cursos";
    }

    // Detalle de un curso
    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id,
                         @AuthenticationPrincipal UserDetails userDetails,
                         Model model) {
        cursoServicio.buscarPorId(id).ifPresent(curso -> {
            model.addAttribute("curso", curso);
            if (userDetails != null) {
                usuarioServicio.buscarPorCorreo(userDetails.getUsername()).ifPresent(u -> {
                    model.addAttribute("estaInscrito", inscripcionServicio.estaInscrito(u, curso));
                    model.addAttribute("usuarioActual", u);
                });
            }
        });
        return "detalle-curso";
    }
}
