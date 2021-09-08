package br.com.fatec.DarkSkull.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/minhaConta", method = RequestMethod.GET)
    public String minhaConta(){
        return "minhaConta";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastro(){
        return "cadastro";
    }

    @RequestMapping("/t")
    public String t(){
        return "clientes/alterar_cartao";
    }

}
