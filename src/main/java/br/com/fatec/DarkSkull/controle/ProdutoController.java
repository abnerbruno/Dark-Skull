package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.model.dominio.usuario.UsuarioSingleton;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static br.com.fatec.DarkSkull.util.constants.*;

@AllArgsConstructor

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final Fachada fachada;
    UsuarioSingleton usuario;

    @GetMapping("/detalhesproduto")
    public ModelAndView detalhesProduto(@RequestParam("id") Long id) {
        EntidadeDominio entidadeDominio = fachada.consultarbyId(Produto.class.getName(), id);
        Produto produto = (Produto) entidadeDominio;

        ModelAndView modelAndView = new ModelAndView("estoque/detalhesproduto");
        modelAndView.addObject("produto", produto);

        return modelAndView;
    }

    @GetMapping("/comprar")
    public ModelAndView comprarProduto(@RequestParam("id") Long id) {
        Produto produto = (Produto) fachada.consultarbyId(Produto.class.getName(), id);
        Cliente cliente = (Cliente) fachada.consultarbyId(Cliente.class.getName(), usuario.getId());

        Endereco endPag = null;
        Endereco endEnv = null;
        for(Endereco end : cliente.getEnderecos()){
            if(end.getComportamento() == PAGAMENTO_E_ENVIO.getCode()){
                endPag = end;
                endEnv = end;
            } else if(end.getComportamento() == PAGAMENTO.getCode()){
                endPag = end;
            } else if(end.getComportamento() == ENVIO.getCode()){
                endEnv = end;
            }
        }

        ModelAndView modelAndView = new ModelAndView("pedidos/checkout");
        modelAndView.addObject("produto", produto);
        modelAndView.addObject("cliente", cliente);
        modelAndView.addObject("enderecopagamento", endPag);
        modelAndView.addObject("enderecoenvio", endEnv);


        return modelAndView;
    }

    @GetMapping(value = "/produtos")
    public String produtos() {
        return "estoque/produtos";
    }

}
