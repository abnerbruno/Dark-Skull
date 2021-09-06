package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.registros.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,Long> {
}
