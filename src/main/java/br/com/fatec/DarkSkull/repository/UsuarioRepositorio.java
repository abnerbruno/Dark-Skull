package br.com.fatec.DarkSkull.repository;


import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {

    Usuario findByEmailAndSenha(String email, String senha);

}
