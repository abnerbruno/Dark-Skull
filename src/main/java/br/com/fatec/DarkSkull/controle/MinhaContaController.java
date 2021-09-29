package br.com.fatec.DarkSkull.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/minhaConta")
public class MinhaContaController {

    @GetMapping
    public String minhaConta() {
        return "minhaConta";
    }

    @RequestMapping("/alterar_cartao")
    public String alterarCartao() {
        return "alterar_cartao";
    }

    @RequestMapping("/alterar_pedido")
    public String alterarPedido() {
        return "Alterar_Endereco";
    }

    @RequestMapping("/alterar_endereco")
    public String alterarEndereco() {
        return "Alterar_Endereco";
    }
}
