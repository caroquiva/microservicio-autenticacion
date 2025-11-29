package autenticacion.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import autenticacion.modelo.Usuario;
import autenticacion.repositorio.RepoUsuario;
import autenticacion.servicio.Servicio;


@RestController
@RequestMapping("/autenticacion/")

public class UsuarioControlador {

	@Autowired
	private Servicio servicio;
	
	

	    @PostMapping("/registro")
	    public String addNewUser(@RequestBody Usuario user) {
	    	
	    	
	        return servicio.guardarUsuario(user);
	    }

	    @PostMapping(path = "/token", produces = "text/plain")
	    
		    public ResponseEntity<String> getToken(@RequestBody Usuario user) {
		    
		    
		    if(this.servicio.verificarUsuario(user)) {
		    	return ResponseEntity.ok(servicio.generateToken(user.getUsername().toUpperCase()));
		    }
		    else {
		    	return ResponseEntity.ok("acceso denegado" );
		    }
		            
	        
	    }

	    @GetMapping("/validate")
	    public String validateToken(@RequestParam("token") String token) {
	        servicio.validateToken(token);
	        return "Token is valid";
	    }
	
}
