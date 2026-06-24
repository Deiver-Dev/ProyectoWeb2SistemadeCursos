package teccr.justdoitcloud.seguridad;

import teccr.justdoitcloud.entidades.Usuario;
import teccr.justdoitcloud.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByCorreo(correo)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));

        // Spring Security necesita el rol con prefijo ROLE_
        SimpleGrantedAuthority autoridad = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name());

        return new org.springframework.security.core.userdetails.User(
            usuario.getCorreo(),
            usuario.getContrasena(),
            usuario.isActivo(),
            true, true, true,
            List.of(autoridad)
        );
    }
}
