package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class HomeController {

    private final Fachada fachada;

    @GetMapping
    public ModelAndView index() {
        List<EntidadeDominio> produtos = (fachada.consultar(Produto.class.getName()));
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

    @GetMapping(value = "/home")
    public ModelAndView home() {
        List<EntidadeDominio> produtos = (fachada.consultar(Produto.class.getName()));
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);
        return modelAndView;
    }

    @GetMapping("/inserirprodutos")
    public String inserirProdutos() {

        Produto produto1 = new Produto();
        produto1.setDescricao("Vestido Preto");
        produto1.setNome("Vestido Preto");
        produto1.setTamanho("P");
        produto1.setQuantidade(5);
        produto1.setValor(66);
        produto1.setImagem("1");

        fachada.salvar(produto1);

        Produto produto2 = new Produto();
        produto2.setDescricao("Vestido Branco");
        produto2.setNome("Vestido Branco");
        produto2.setTamanho("PP");
        produto2.setQuantidade(5);
        produto2.setValor(70);
        produto2.setImagem("2");

        fachada.salvar(produto2);

        Produto produto3 = new Produto();
        produto3.setDescricao("Vestido Azul");
        produto3.setNome("Vestido Azul");
        produto3.setTamanho("M");
        produto3.setQuantidade(5);
        produto3.setValor(109);
        produto3.setImagem("3");

        fachada.salvar(produto3);

        Produto produto4 = new Produto();
        produto4.setDescricao("Vestido Vermelho");
        produto4.setNome("Vestido Vermelho");
        produto4.setTamanho("G");
        produto4.setQuantidade(5);
        produto4.setValor(88);
        produto4.setImagem("4");

        fachada.salvar(produto4);

        Produto produto5 = new Produto();
        produto5.setDescricao("Camisa de Algodão");
        produto5.setNome("Camisa Loka");
        produto5.setTamanho("GG");
        produto5.setQuantidade(5);
        produto5.setValor(100);
        produto5.setImagem("5");

        fachada.salvar(produto5);

        return "home";
    }

}
