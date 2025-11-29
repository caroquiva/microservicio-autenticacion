package autenticacion.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import autenticacion.modelo.Usuario;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario,Long>{

	public Optional<Usuario> findByUsername(String username);
	public Optional<Usuario> findByPassword(String password);
}
