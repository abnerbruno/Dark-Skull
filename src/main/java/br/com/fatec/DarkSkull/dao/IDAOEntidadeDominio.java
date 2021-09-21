package br.com.fatec.DarkSkull.dao;

import br.com.fatec.DarkSkull.model.EntidadeDominio;

import java.util.List;

public interface IDAOEntidadeDominio {

    public List<?> findAll();
    public EntidadeDominio getById(Long id);
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidade);
    public void deletedById(Long id);
    public void deleted(EntidadeDominio entidade);
}
