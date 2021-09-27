package br.com.fatec.DarkSkull.fachada;

import br.com.fatec.DarkSkull.dao.CartaoDao;
import br.com.fatec.DarkSkull.dao.ClienteDao;
import br.com.fatec.DarkSkull.dao.EnderecoDao;
import br.com.fatec.DarkSkull.dao.IDAOEntidadeDominio;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.model.dominio.cliente.cartao.Cartao;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
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

    @Autowired
    private final EnderecoDao enderecoDao;

    @Autowired
    private final CartaoDao cartaoDao;

    private Map<String, IDAOEntidadeDominio> listaRepository;

    public void antesProcessar() {
        this.listaRepository = new HashMap<>();
        listaRepository.put(Cliente.class.getName(), this.clienteDao);
        listaRepository.put(Endereco.class.getName(), this.enderecoDao);
        listaRepository.put(Cartao.class.getName(), this.cartaoDao);
    }

    @Override
    public String salvar(EntidadeDominio entidade) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        repository.saveOrUpdate(entidade);
        return "mensagens/inserido";
    }

    @Override
    public String alterar(EntidadeDominio entidade) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        repository.saveOrUpdate(entidade);
        return "mensagens/alterado";
    }

    @Override
    public String excluir(EntidadeDominio entidade) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        repository.deletedById(entidade.getId());
        return "mensagens/excluido";
    }

    public String excluirById(String classname, Long id) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(classname);
        repository.deletedById(id);
        return "mensagens/excluido";
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        return (List<EntidadeDominio>) repository.findAll();
    }

    public EntidadeDominio consultarbyId(String classname, Long id) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(classname);
        return repository.getById(id);
    }


}
