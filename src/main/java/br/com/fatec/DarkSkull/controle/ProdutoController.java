package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final Fachada fachada;

    @GetMapping("/detalhesproduto")
    public ModelAndView detalhesProduto(@RequestParam("id") Long id) {
        EntidadeDominio entidadeDominio = fachada.consultarbyId(Produto.class.getName(), id);
        Produto produto = (Produto) entidadeDominio;

        ModelAndView modelAndView = new ModelAndView("estoque/detalhesproduto");
        modelAndView.addObject("produto", produto);

        return modelAndView;
    }

    @GetMapping(value = "/produtos")
    public String produtos() {
        return "estoque/produtos";
    }

}
