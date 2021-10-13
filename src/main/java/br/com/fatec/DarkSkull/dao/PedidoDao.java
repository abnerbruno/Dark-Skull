package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.pedido.Pedido;
import br.com.fatec.DarkSkull.repository.PedidoRepositorio;
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
public class PedidoDao implements IDAOEntidadeDominio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Override
    public List<Pedido> findAll() {
        return this.pedidoRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {

        Optional pedido = this.pedidoRepositorio.findById(id);
        return (EntidadeDominio) pedido.get();
    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        return this.pedidoRepositorio.save((Pedido) entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.pedidoRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.pedidoRepositorio.delete((Pedido) entidade);
    }

}
