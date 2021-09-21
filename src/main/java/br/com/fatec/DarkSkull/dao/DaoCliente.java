package br.com.fatec.DarkSkull.dao;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor

@Service
@Profile("jpadao")
public class DaoCliente implements IDAOEntidadeDominio {


    private EntityManagerFactory emf;

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Cliente> listAll() {

        EntityManager em = emf.createEntityManager();

        return em.createQuery("from cliente", Cliente.class).getResultList();
    }

    @Override
    public EntidadeDominio getById(Long id) {

        EntityManager em = emf.createEntityManager();

        return em.find(Cliente.class, id);
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        EntidadeDominio cliente = em.merge(entidadeDominio);
        em.getTransaction().commit();

        return cliente;
    }

    @Override
    public void deletedById(Long id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Cliente.class, id));
        em.getTransaction().commit();
    }
}
