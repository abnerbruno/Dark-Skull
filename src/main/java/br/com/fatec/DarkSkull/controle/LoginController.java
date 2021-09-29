package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/login")
public class LoginController {

    private final Fachada fachada;

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public ModelAndView loginUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha ) {

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        usuario = (Usuario) fachada.consultarEntidade(usuario);
        if(usuario == null){
            return new ModelAndView("clientes/cadastro");
        }

        List<EntidadeDominio> produtos = (fachada.consultar(Produto.class.getName()));
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("produtos", produtos);
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }

    @GetMapping(value = "/cadastro")
    public String cadastro() {
        return "clientes/cadastro";
    }




}
