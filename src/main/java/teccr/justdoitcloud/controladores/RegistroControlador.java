package teccr.justdoitcloud.controladores;

import teccr.justdoitcloud.entidades.RolUsuario;
import teccr.justdoitcloud.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registro")
    public String mostrarFormulario() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrar(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String contrasena,
            @RequestParam String rol,
            Model model) {
        try {
            RolUsuario rolUsuario = RolUsuario.valueOf(rol.toUpperCase());
            usuarioServicio.registrar(nombre, correo, contrasena, rolUsuario);
            return "redirect:/login?registrado=true";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }
}
