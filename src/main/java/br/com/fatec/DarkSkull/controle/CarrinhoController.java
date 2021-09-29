package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final Fachada fachada;

    @GetMapping
    public String carrinho() {
        return "pedidos/carrinho";
    }



}
