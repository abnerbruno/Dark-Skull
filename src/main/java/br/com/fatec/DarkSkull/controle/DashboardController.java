package br.com.fatec.DarkSkull.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @RequestMapping("/cadastro")
    public String cadastrarCliente() {
        return "cadastro";
    }

    @RequestMapping("/alterar_pedido")
    public String alterarPedido() {
        return "alterar_pedido";
    }

    @RequestMapping("/alterar_cliente")
    public String alterarCliente() { return "alterar_cliente";
    }



}
