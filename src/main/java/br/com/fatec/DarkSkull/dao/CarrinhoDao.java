package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.pedido.Carrinho;
import br.com.fatec.DarkSkull.repository.CarrinhoRepositorio;
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
public class CarrinhoDao implements IDAOEntidadeDominio {

    @Autowired
    private CarrinhoRepositorio carrinhoRepositorio;

    @Override
    public List<Carrinho> findAll() {
        return this.carrinhoRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {
        Optional carrinho = this.carrinhoRepositorio.findById(id);
        return (EntidadeDominio) carrinho.get();
    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        return this.carrinhoRepositorio.save((Carrinho) entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.carrinhoRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.carrinhoRepositorio.delete((Carrinho) entidade);
    }

}
