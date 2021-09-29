package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.repository.ProdutoRepositorio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter

@NoArgsConstructor
@Service
public class ProdutoDao implements IDAOEntidadeDominio {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;


    @Override
    public List<Produto> findAll() {
        return this.produtoRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {

        Optional produto = this.produtoRepositorio.findById(id);
        EntidadeDominio entidade = (EntidadeDominio) produto.get();

        return entidade;
    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        return (Produto) this.produtoRepositorio.save(entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.produtoRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.produtoRepositorio.delete((Cliente) entidade);
    }

}
