package br.com.fatec.DarkSkull.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String main(){
        return "Home";
    }

    @RequestMapping("/Home") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String home(){
        return "Home";
    }

    @RequestMapping("/Produto") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String produto(){
        return "Home";
    }

    @RequestMapping("/MinhaConta") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String minhaConta(){
        return "MinhaConta";
    }

    @RequestMapping("/Login") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String login(){
        return "Login";
    }

    @RequestMapping("/Cadastro") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String cadastrar(){
        return "Cadastro";
    }

    @RequestMapping("/Dashboard") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public String dashboard(){
        return "Dashboard";
    }

}
