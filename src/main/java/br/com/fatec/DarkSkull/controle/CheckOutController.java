package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.model.dominio.cliente.cartao.Cartao;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.pedido.Pedido;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import br.com.fatec.DarkSkull.model.dominio.usuario.UsuarioSingleton;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import static br.com.fatec.DarkSkull.util.constants.*;

@AllArgsConstructor
@Controller
@RequestMapping("/checkout")
public class CheckOutController {

    private final Fachada fachada;
    UsuarioSingleton usuario;

    @GetMapping
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

        Cartao cartaoPrincipal = null;
        for (Cartao cartao : cliente.getCartoes()) {
            if (cartao.getComportamento() == PRINCIPAL.getCode()) {
                cartaoPrincipal = cartao;
            }
        }

        ModelAndView modelAndView = new ModelAndView("pedidos/checkout");
        modelAndView.addObject("produto", produto);
        modelAndView.addObject("cliente", cliente);
        modelAndView.addObject("enderecopagamento", endPag);
        modelAndView.addObject("enderecoenvio", endEnv);
        modelAndView.addObject("cartaopricipal", cartaoPrincipal);


        return modelAndView;
    }

    @PostMapping
    public String finalizarCompraProduto(@RequestParam("produtoid") Long id) {
        Produto produto = (Produto) fachada.consultarbyId(Produto.class.getName(), id);
        Pedido pedido = new Pedido();

        pedido.setNomeProduto(produto.getNome());
        pedido.setDataVenda(new Date());
        pedido.setTamanho("GG");
        pedido.setValorTotal(500L);

        pedido.setStatus("Em Processo");

        Usuario usuario = new Usuario();
        usuario.setEmail(this.usuario.getEmail());
        usuario.setSenha(this.usuario.getSenha());
        usuario.setStatus(this.usuario.getStatus());
        usuario.setId(this.usuario.getId());

        pedido.setUsuario(usuario);

        fachada.salvar(pedido);





        return "mensagens/compra";
    }


}
