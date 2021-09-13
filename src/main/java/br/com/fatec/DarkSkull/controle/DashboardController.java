package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.dao.ClienteRepositorio;
import br.com.fatec.DarkSkull.dao.EnderecoRepositorio;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.*;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.util.ComportamentoEndereco;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ClienteRepositorio clienteRepositorio;
    private final EnderecoRepositorio enderecoRepositorio;

    @GetMapping
    public ModelAndView listarTudo() {
        List<Cliente>clientes = this.clienteRepositorio.findAll();

        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("clientes", clientes);
        return modelAndView;
    }


    @GetMapping("/cadastro_cliente")
    public String cadastrarCliente() {
        return "clientes/cadastro";
    }

    @PostMapping("/cadastro_cliente")
    public String salvarCliente(@RequestParam Map<String,String> allParamsCliente) throws ParseException {
        String nome = allParamsCliente.get("nome");
        String email = allParamsCliente.get("email");
        String telefone = allParamsCliente.get("telefone");
        String cpf = allParamsCliente.get("cpf");
        String genero = allParamsCliente.get("genero");
        String senha = allParamsCliente.get("senha");
        String sanhaconfirmacao = allParamsCliente.get("sanhaconfirmacao");

        String dataNascimento = allParamsCliente.get("datanascimento");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(dataNascimento);
        Timestamp timestampDataNascimento = new java.sql.Timestamp(parsedDate.getTime());

        Estado estado = new Estado();
        estado.setNome(allParamsCliente.get("estado"));

        Cidade cidade = new Cidade();
        cidade.setEstado(estado);
        cidade.setNome(allParamsCliente.get("cidade"));
        cidade.setDescricao(allParamsCliente.get("complemento"));
        cidade.setBairro(allParamsCliente.get("bairro"));

        Endereco enderecoPagamento = new Endereco();
        enderecoPagamento.setCidade(cidade);
        enderecoPagamento.setLongadouro(allParamsCliente.get("logradouro"));
        enderecoPagamento.setNumero(allParamsCliente.get("numero"));
        enderecoPagamento.setTipoResidencia(allParamsCliente.get("tipoendereco"));
        enderecoPagamento.setCep(allParamsCliente.get("cep"));
        enderecoPagamento.setDescricao("Endereço de Pagamento de Usuario");
        enderecoPagamento.setComportamento(ComportamentoEndereco.PAGAMENTO.getCode());

        Endereco enderecoEnvio = new Endereco();
        enderecoEnvio.setCidade(cidade);
        enderecoEnvio.setLongadouro(allParamsCliente.get("logradouro"));
        enderecoEnvio.setNumero(allParamsCliente.get("numero"));
        enderecoEnvio.setTipoResidencia(allParamsCliente.get("tipoendereco"));
        enderecoEnvio.setCep(allParamsCliente.get("cep"));
        enderecoEnvio.setDescricao("Endereço de Envio de Usuario");
        enderecoEnvio.setComportamento(ComportamentoEndereco.ENVIO.getCode());

        Cliente cliente = new Cliente(email, senha, nome, cpf, timestampDataNascimento, genero, telefone, enderecoPagamento, enderecoEnvio);

        this.clienteRepositorio.save(cliente);
        return "mensagens/inserido";
    }

    @GetMapping("/excluir_cliente")
    public String excluirCliente(@RequestParam("id") Long id) {
        this.clienteRepositorio.deleteById(id);
        return "mensagens/excluido";
    }


}
