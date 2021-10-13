package br.com.fatec.DarkSkull.repository;


import br.com.fatec.DarkSkull.model.dominio.pedido.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepositorio extends JpaRepository<Cupom,Long> {

}
