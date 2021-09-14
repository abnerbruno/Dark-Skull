package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepositorio extends JpaRepository<Endereco,Long> {

    Endereco findByid(Long id);
    Endereco findByClienteIdAndComportamento(Long id, int comportamento);
    //o nome da função influencia de como a query é gerada será necessario colocar "findBy" seguido do nome dos paramatros a ser buscado
}
