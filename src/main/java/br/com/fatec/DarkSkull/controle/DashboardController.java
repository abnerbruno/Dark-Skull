package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.dao.ClienteRepositorio;
import br.com.fatec.DarkSkull.dao.EnderecoRepositorio;
import br.com.fatec.DarkSkull.model.dominio.endereco.Cidade;
import br.com.fatec.DarkSkull.model.dominio.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.endereco.Estado;
import br.com.fatec.DarkSkull.model.dominio.usuario.Cliente;
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
        estado.setNome(allParamsCliente.get("Estado"));

        Cidade cidade = new Cidade();
        cidade.setEstado(estado);
        cidade.setNome(allParamsCliente.get("cidade"));
        cidade.setBairro(allParamsCliente.get("bairro"));


        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);
        endereco.setLongadouro(allParamsCliente.get("logradouro"));
        endereco.setTipo(allParamsCliente.get("tipoendereco"));
        endereco.setCep(allParamsCliente.get("cep"));

        Cliente cliente = new Cliente(email, senha, nome, cpf, timestampDataNascimento, genero, telefone, endereco);

        this.clienteRepositorio.save(cliente);
        return "mensagens/inserido";
    }

    @GetMapping("/excluir_cliente")
    public String excluirCliente(@RequestParam("id") Long id) {
        this.clienteRepositorio.deleteById(id);
        return "mensagens/excluido";
    }


}