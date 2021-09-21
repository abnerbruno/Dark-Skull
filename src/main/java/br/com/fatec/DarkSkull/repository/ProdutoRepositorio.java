package br.com.fatec.DarkSkull.repository;


import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio<T extends Produto> extends JpaRepository<T,Long> {

}
