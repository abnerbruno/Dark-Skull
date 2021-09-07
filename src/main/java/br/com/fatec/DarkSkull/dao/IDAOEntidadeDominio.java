package br.com.fatec.DarkSkull.dao;

import java.util.List;

public interface IDAOEntidadeDominio {

    public List<?> listAll();
    public <T> T getById(int id);
//    public <T> T saveOrUpdate(EndidadeDominio T);
    public void deletedById(int id);
}
