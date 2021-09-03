package br.com.fatec.DarkSkull.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessageController {

    @RequestMapping("/inativado")
    public String mensagemInativado() {
        return "inativado";
    }

    @RequestMapping("/excluido")
    public String mensagemExcluido() {
        return "excluido";
    }

}
