package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.registros.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Long> {
}
