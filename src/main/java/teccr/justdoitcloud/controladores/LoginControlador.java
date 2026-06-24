package teccr.justdoitcloud.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginControlador {

    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {
        if (error != null) model.addAttribute("error", "Correo o contrasena incorrectos");
        if (logout != null) model.addAttribute("mensaje", "Sesion cerrada correctamente");
        return "login";
    }

    @GetMapping("/")
    public String inicio() {
        return "redirect:/cursos";
    }
}
