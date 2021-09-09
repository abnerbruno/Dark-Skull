package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.dominio.endereco.EnderecoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoPagamentoRepositorio extends JpaRepository<EnderecoPagamento,Long> {
}
