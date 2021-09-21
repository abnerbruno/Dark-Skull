package br.com.fatec.DarkSkull.fachada;

import br.com.fatec.DarkSkull.dao.ClienteDao;
import br.com.fatec.DarkSkull.dao.IDAOEntidadeDominio;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor

@Service
public class Fachada implements IFachada {

    @Autowired
    private final ClienteDao clienteDao;

    private Map<String, IDAOEntidadeDominio> listaRepository;

    public void inserirdaos() {
        this.listaRepository = new HashMap<>();
        listaRepository.put(Cliente.class.getName(), this.clienteDao);
    }

    @Override
    public String salvar(EntidadeDominio entidade) {
        inserirdaos();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        repository.saveOrUpdate(entidade);
        return "mensagens/inserido";
    }

    @Override
    public String alterar(EntidadeDominio entidade) {
        inserirdaos();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        repository.saveOrUpdate(entidade);
        return "mensagens/alterado";
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        inserirdaos();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        repository.deletedById(entidade.getId());
        return "mensagens/excluido";
    }

    public String excluirById(EntidadeDominio entidade, Long id) {
        inserirdaos();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        repository.deletedById(id);
        return "mensagens/excluido";
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        inserirdaos();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        repository.findAll();

        return (List<EntidadeDominio>) repository.findAll();
    }
}
