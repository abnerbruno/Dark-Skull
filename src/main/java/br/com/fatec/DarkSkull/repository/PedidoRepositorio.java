package br.com.fatec.DarkSkull.repository;


import br.com.fatec.DarkSkull.model.dominio.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido,Long> {

}
