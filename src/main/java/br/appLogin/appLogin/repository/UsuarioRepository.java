package br.appLogin.appLogin.repository;

import br.appLogin.appLogin.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {

    Usuario findById(Long id);

    @Query(value = "select * from applogin.usuario where email = :email and senha = :senha", nativeQuery = true)
    public Usuario login(String email, String senha);
}
