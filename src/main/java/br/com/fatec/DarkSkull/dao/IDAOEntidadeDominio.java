package br.com.fatec.DarkSkull.dao;

import br.com.fatec.DarkSkull.model.EntidadeDominio;

import java.util.List;

public interface IDAOEntidadeDominio {

    public List<?> listAll();
    public EntidadeDominio getById(Long id);
    public EntidadeDominio saveOrUpdate(EntidadeDominio T);
    public void deletedById(Long id);
}
