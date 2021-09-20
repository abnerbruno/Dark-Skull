package br.com.fatec.DarkSkull.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String index() {
        return "home";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/minhaConta")
    public String minhaConta() {
        return "minhaConta";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @GetMapping(value = "/produtos")
    public String produtos() {
        return "produtos";
    }

    @GetMapping(value = "/carrinho")
    public String carrinho() {
        return "carrinho";
    }

    @GetMapping(value = "/listadesejo")
    public String listadesejo() {
        return "listadesejo";
    }

    @GetMapping(value = "/checkout")
    public String checkout() {
        return "checkout";
    }

    @GetMapping(value = "/detalhesproduto")
    public String detalhesproduto() {
        return "detalhesproduto";
    }

    @GetMapping(value = "/alterar_pedido")
    public String alterarPedido() {
        return "pedidos/alterar_pedido";
    }

    @GetMapping("/t")
    public String t(){
        return "clientes/alterar_cartao";
    }

}
