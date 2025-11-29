package autenticacion.configuracion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import autenticacion.modelo.Usuario;
import autenticacion.repositorio.RepoUsuario;


@Component
public class CustomUserDetailService implements UserDetailsService{

	
	 @Autowired
	    private RepoUsuario repository;

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<Usuario> credential = repository.findByUsername(username);
	        return credential.map(CustomerUserDetail::new).orElseThrow(() -> new UsernameNotFoundException("user not found with name :" + username));
	    }
}
