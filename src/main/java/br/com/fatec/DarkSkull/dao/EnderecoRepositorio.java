package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.dominio.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco,Long> {
}
