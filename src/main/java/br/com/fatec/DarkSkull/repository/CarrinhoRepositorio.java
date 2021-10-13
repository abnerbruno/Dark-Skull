package br.com.fatec.DarkSkull.repository;


import br.com.fatec.DarkSkull.model.dominio.pedido.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepositorio extends JpaRepository<Carrinho,Long> {

}
