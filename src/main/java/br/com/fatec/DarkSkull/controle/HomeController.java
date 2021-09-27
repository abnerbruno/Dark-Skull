package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class HomeController {

    private final Fachada fachada;

    @GetMapping(value = "/")
    public ModelAndView index() {
        Optional<List<EntidadeDominio>> produtos = Optional.ofNullable(fachada.consultar(Produto.class.getName()));
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
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
