package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.dao.ClienteRepositorio;
import br.com.fatec.DarkSkull.model.registros.Cliente;
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
import java.util.Optional;

@AllArgsConstructor

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ClienteRepositorio clienteRepositorio;

    @GetMapping
    public ModelAndView listarTudo() {
        List<Cliente>clientes = this.clienteRepositorio.findAll();

        ModelAndView modelAndView = new ModelAndView("dashboard");
        modelAndView.addObject("clientes", clientes);
        return modelAndView;
    }


    @GetMapping("/cadastro_cliente")
    public String cadastrarCliente() {
        return "cadastro";
    }

    @PostMapping("/cadastro_cliente")
    public String salvarCliente(@RequestParam Map<String,String> allParamsCliente) throws ParseException {
        String nome = allParamsCliente.get("name");
        String dataNascimento = allParamsCliente.get("DtNascimento");
        String email = allParamsCliente.get("email");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(dataNascimento);
        Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

        Cliente cliente = new Cliente(nome, timestamp, email);


        this.clienteRepositorio.save(cliente);
        return "inserido";
    }

    @GetMapping("/excluir_cliente")
    public String excluirCliente(@RequestParam("id") Long id) {
        this.clienteRepositorio.deleteById(id);
        return "excluido";
    }

    @RequestMapping("/alterar_pedido")
    public String alterarPedido() {
        return "alterar_pedido";
    }




}
