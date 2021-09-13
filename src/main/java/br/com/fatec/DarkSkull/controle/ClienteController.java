package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.dao.ClienteRepositorio;
import br.com.fatec.DarkSkull.dao.EnderecoRepositorio;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.util.ComportamentoEndereco;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static br.com.fatec.DarkSkull.util.ComportamentoEndereco.*;

@AllArgsConstructor

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepositorio clienteRepositorio;
    private final EnderecoRepositorio enderecoRepositorio;

    @GetMapping()
    public ModelAndView alterarCliente(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("clientes/cliente");

        Optional<Cliente> Opcionalcliente = this.clienteRepositorio.findById(id);
        Cliente cliente = Opcionalcliente.get();
        //Buscar Cliente no Banco

        List<Endereco> enderecos = cliente.getEnderecoList();
        Endereco endPagAndEnv = this.enderecoRepositorio.findByIdAndComportamento(cliente.getId(), PAGAMENTO_E_ENVIO.getCode());
        Endereco endPag = this.enderecoRepositorio.findByIdAndComportamento(cliente.getId(), ENVIO.getCode());
        Endereco endEnv = this.enderecoRepositorio.findByIdAndComportamento(cliente.getId(), PAGAMENTO.getCode());
        // Buscar Lista de enterços no banco

        modelAndView.addObject("cliente", cliente);
        modelAndView.addObject("enderecos", enderecos);


        if(endPagAndEnv != null){
            modelAndView.addObject("enderecopagamento", endPagAndEnv);
            modelAndView.addObject("enderecoenvio", endPagAndEnv);
        } else {
            modelAndView.addObject("enderecopagamento", endPag);
            modelAndView.addObject("enderecoenvio", endEnv);
        }

        return modelAndView;
    }

    @PostMapping("/atualizar_dados")
    public String atualizarDados(@RequestParam Map<String,String> allParamsCliente) throws ParseException {

        Long id = Long.valueOf(allParamsCliente.get("id"));
        Optional<Cliente> opcionalCliente = this.clienteRepositorio.findById(id);

        String nome = allParamsCliente.get("nome");
        String email = allParamsCliente.get("usuario.email");
        String telefone = allParamsCliente.get("telefone");
        String cpf = allParamsCliente.get("cpf");
        String genero = allParamsCliente.get("genero");

        Cliente cliente = opcionalCliente.get();
        cliente.setNome(nome);
        cliente.getUsuario().setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setGenero(genero);

        this.clienteRepositorio.save(cliente);
        return "mensagens/alterado";
    }

    @PostMapping("/atualizar_senha")
    public String atualizarSenha(@RequestParam Map<String,String> allParamsCliente) {

        Long id = Long.valueOf(allParamsCliente.get("id"));
        Optional<Cliente> opcionalCliente = this.clienteRepositorio.findById(id);

        String senha = allParamsCliente.get("usuario.senha");
        String novasenha = allParamsCliente.get("novasenha");
        String confirmarnovasenha = allParamsCliente.get("confirmarnovasenha");


        Cliente cliente = opcionalCliente.get();
        cliente.getUsuario().setSenha(novasenha);

        this.clienteRepositorio.save(cliente);
        return "mensagens/alterado";
    }

    @GetMapping("/inativado")
    public String inativarCliente(@RequestParam("id") Long id) {
        Optional<Cliente> opcionalCliente = this.clienteRepositorio.findById(id);
        Cliente cliente = opcionalCliente.get();
        cliente.getUsuario().setStatus("Inativado");
        this.clienteRepositorio.save(cliente);
        return "mensagens/inativado";
    }

    @GetMapping("/alterar_endereco")
    public ModelAndView buscarClienteEndereco(@RequestParam("id") Long id) {

        Optional<Endereco> OpcionalEndereco = this.enderecoRepositorio.findById(id);
        Endereco endereco = OpcionalEndereco.get();

        ModelAndView modelAndView = new ModelAndView("clientes/alterar_endereco");
        modelAndView.addObject("endereco", endereco);

        return modelAndView;
    }

    @RequestMapping("/alterar_cartao")
    public String alterarClienteCartao() {
        return "alterar_cartao";
    }

    @RequestMapping("/alterar_pedido")
    public String alterarClientePedido() {
        return "alterar_pedido";
    }

}
