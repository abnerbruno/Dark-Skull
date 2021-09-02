package br.com.fatec.DarkSkull.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MinhaContaController {
    @RequestMapping("/MinhaConta/Pedido") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String minhaContaDescricaoPedido(){
        return "Descricao_Pedido";
    }

    @RequestMapping("MinhaConta/Alterar_Cartao") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String minhaContaCartao(){
        return "Alterar_Cartao";
    }

    @RequestMapping("MinhaConta/Alterar_Endereco") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String minhaContaEndereco(){
        return "Alterar_Endereco";
    }

}
