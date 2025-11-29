package autenticacion.servicio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import autenticacion.modelo.Usuario;
import autenticacion.repositorio.RepoUsuario;

@Service
public class Servicio {

	
	@Autowired
	private RepoUsuario repositorioU;
	
	
	@Autowired
	private PasswordEncoder passwordEncript;
	
	@Autowired
	private JWTServicio jwtService;
	
	
	public String guardarUsuario(Usuario u) {
		
		Optional<Usuario> usuario = this.repositorioU.findByUsername(u.getUsername());
		
		if(!usuario.isEmpty()) {
			return "ya existe";
		}
		else {
			
			u.setPassword(passwordEncript.encode(u.getPassword()));
			this.repositorioU.save(u);
			return "usuario creado";
		}
		
	}
	
	public boolean verificarUsuario(Usuario u) {
		
		Optional<Usuario> usuario = this.repositorioU.findByUsername(u.getUsername());
		
		
		if(!usuario.isEmpty()) {
			String storedHashedPassword = usuario.get().getPassword();

	 
	        return passwordEncript.matches(u.getPassword(), storedHashedPassword);
		}
		
			
			return false;
		
		
	}
	
	public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
