package br.com.fatec.DarkSkull.repository;


import br.com.fatec.DarkSkull.model.dominio.cliente.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepositorio extends JpaRepository<Cartao,Long> {
    Cartao findByClienteId(Long id);
    Cartao findByClienteIdAndComportamento(Long id, int comportamento);
}
