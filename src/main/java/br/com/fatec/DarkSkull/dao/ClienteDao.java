package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.repository.ClienteRepositorio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter

@NoArgsConstructor
@Service
public class ClienteDao implements IDAOEntidadeDominio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;


    @Override
    public List<Cliente> findAll() {
        return this.clienteRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {
        return this.clienteRepositorio.getById(id);
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        return this.clienteRepositorio.save((Cliente) entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.clienteRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.clienteRepositorio.delete((Cliente) entidade);
    }
}
