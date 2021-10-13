package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.pedido.Envio;
import br.com.fatec.DarkSkull.model.dominio.pedido.ListaDesejo;
import br.com.fatec.DarkSkull.repository.EnvioRepositorio;
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
public class EnvioDao implements IDAOEntidadeDominio {

    @Autowired
    private EnvioRepositorio envioRepositorio;

    @Override
    public List<Envio> findAll() {
        return this.envioRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {
        Optional envio = this.envioRepositorio.findById(id);
        return (EntidadeDominio) envio.get();
    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        return this.envioRepositorio.save((Envio) entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.envioRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.envioRepositorio.delete((Envio) entidade);
    }

}
