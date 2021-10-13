package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.pedido.Cupom;
import br.com.fatec.DarkSkull.model.dominio.pedido.Envio;
import br.com.fatec.DarkSkull.repository.CupomRepositorio;
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
public class CupomDao implements IDAOEntidadeDominio {

    @Autowired
    private CupomRepositorio cupomRepositorio;

    @Override
    public List<Cupom> findAll() {
        return this.cupomRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {
        Optional cupom = this.cupomRepositorio.findById(id);
        return (EntidadeDominio) cupom.get();
    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        return this.cupomRepositorio.save((Cupom) entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.cupomRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.cupomRepositorio.delete((Cupom) entidade);
    }

}
