package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.dao.ClienteRepositorio;
import br.com.fatec.DarkSkull.model.registros.Cliente;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepositorio clienteRepositorio;

    @GetMapping()
    public ModelAndView alterarCliente(@RequestParam("id") Long id) {
        Optional<Cliente> cliente = this.clienteRepositorio.findById(id);
        ModelAndView modelAndView = new ModelAndView("cliente");
        modelAndView.addObject("cliente", cliente);
        return modelAndView;
    }

    @PostMapping("/atualizar_dados")
    public String atualizarDados(@RequestParam Map<String,String> allParamsCliente) throws ParseException {
        Long id = Long.parseLong(allParamsCliente.get("id"));
        String nome = allParamsCliente.get("nome");
//        String dataNascimento = allParamsCliente.get("dataNascimento");
        String email = allParamsCliente.get("email");

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
//        Date parsedDate = dateFormat.parse(dataNascimento);
//        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

        Cliente cliente = new Cliente(id, nome, email);

        this.clienteRepositorio.save(cliente);
        return "alterado";
    }

    @RequestMapping("/alterar_cartao")
    public String alterarClienteCartao() {
        return "alterar_cartao";
    }

    @RequestMapping("/alterar_pedido")
    public String alterarClientePedido() {
        return "alterar_pedido";
    }

    @RequestMapping("/alterar_endereco")
    public String alterarClienteEndereco() {
        return "alterar_endereco";
    }
}
