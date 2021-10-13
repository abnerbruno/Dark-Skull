package br.com.fatec.DarkSkull.fachada;

import br.com.fatec.DarkSkull.dao.*;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.model.dominio.cliente.cartao.Cartao;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.pedido.*;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor

@Service
public class Fachada implements IFachada {

    private final ClienteDao clienteDao;
    private final EnderecoDao enderecoDao;
    private final CartaoDao cartaoDao;
    private final ProdutoDao produtoDao;
    private final UsuarioDao usuarioDao;
    private final CarrinhoDao carrinhoDao;
    private final CupomDao cupomDao;
    private final EnvioDao envioDao;
    private final ListaDesejoDao listaDesejoDao;
    private final PedidoDao pedidoDao;

    private Map<String, IDAOEntidadeDominio> listaRepository;

    public void antesProcessar() {
        this.listaRepository = new HashMap<>();
        listaRepository.put(Cliente.class.getName(), this.clienteDao);
        listaRepository.put(Endereco.class.getName(), this.enderecoDao);
        listaRepository.put(Cartao.class.getName(), this.cartaoDao);
        listaRepository.put(Produto.class.getName(), this.produtoDao);
        listaRepository.put(Usuario.class.getName(), this.usuarioDao);
        listaRepository.put(Carrinho.class.getName(), this.carrinhoDao);
        listaRepository.put(Cupom.class.getName(), this.cupomDao);
        listaRepository.put(Envio.class.getName(), this.envioDao);
        listaRepository.put(ListaDesejo.class.getName(), this.listaDesejoDao);
        listaRepository.put(Pedido.class.getName(), this.pedidoDao);
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
    public List<EntidadeDominio> consultar(String entidadeClassName) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(entidadeClassName);
        return (List<EntidadeDominio>) repository.findAll();
    }

    public EntidadeDominio consultarEntidade(EntidadeDominio entidade) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(entidade.getClass().getName());
        return repository.getEntidade(entidade);
    }

    public EntidadeDominio consultarbyId(String classname, Long id) {
        antesProcessar();
        IDAOEntidadeDominio repository = listaRepository.get(classname);
        return repository.getById(id);
    }


}
