package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.*;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.util.constants;
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

    private final Fachada fachada;

    @GetMapping
    public ModelAndView listarTudo() {
        List<EntidadeDominio> clientes = fachada.consultar(Cliente.class.getName());

        ModelAndView modelAndView = new ModelAndView("dashboard/dashboard");
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

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);
        endereco.setLongadouro(allParamsCliente.get("logradouro"));
        endereco.setNumero(allParamsCliente.get("numero"));
        endereco.setTipoResidencia(allParamsCliente.get("tipoendereco"));
        endereco.setCep(allParamsCliente.get("cep"));
        endereco.setDescricao("Endereço de Padrão de Usuario");
        endereco.setComportamento(constants.PAGAMENTO_E_ENVIO.getCode());


        Cliente cliente = new Cliente(email, senha, nome, cpf, timestampDataNascimento, genero, telefone, endereco);
        this.fachada.salvar(cliente);
        return "mensagens/inserido";
    }

    @GetMapping("/excluir_cliente")
    public String excluirCliente(@RequestParam("id") Long id) {
        fachada.excluirById(Cliente.class.getName(), id);
        return "mensagens/excluido";
    }

    @GetMapping(value = "/alterar_pedido")
    public String alterarPedido() {
        return "pedidos/alterar_pedido";
    }

}
