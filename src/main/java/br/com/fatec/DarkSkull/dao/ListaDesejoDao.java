package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.pedido.Carrinho;
import br.com.fatec.DarkSkull.model.dominio.pedido.ListaDesejo;
import br.com.fatec.DarkSkull.repository.ListaDesejoRepositorio;
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
public class ListaDesejoDao implements IDAOEntidadeDominio {

    @Autowired
    private ListaDesejoRepositorio listaDesejoRepositorio;

    @Override
    public List<ListaDesejo> findAll() {
        return this.listaDesejoRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {
        Optional listaDesejo = this.listaDesejoRepositorio.findById(id);
        return (EntidadeDominio) listaDesejo.get();
    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        return this.listaDesejoRepositorio.save((ListaDesejo) entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.listaDesejoRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.listaDesejoRepositorio.delete((ListaDesejo) entidade);
    }

}
