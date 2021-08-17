package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.model.registros.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TesteController {

    @GetMapping("/") //retorna o arquivo chamado index
    public String indexFormulario(){
        return "minimalista/login";
    }

    @GetMapping("/Cadastro") //retorna o arquivo chamado index
    public String usuarioCadastro(){
        return "minimalista/Cadastro";
    }

    @GetMapping("/Alterar_Endereco") //retorna o arquivo chamado index
    public String usuarioListar(){ return "minimalista/Alterar_Endereco"; }

    @GetMapping("/Login") //retorna o arquivo chamado index
    public String usuarioLogin(){ return "minimalista/Login"; }

    @GetMapping("/shop") //retorna o arquivo chamado index
    public String shop(){
        return "shop";
    }

    @GetMapping("/blog") //retorna o arquivo chamado index
    public String blog(){
        return "blog";
    }

    @GetMapping("/elements") //retorna o arquivo chamado index
    public String elements(){
        return "elements";
    }


    @GetMapping("/portfolio-item.html") //retorna o arquivo chamado index
    public String portfolioitem(){
        return "portfolio-item";
    }

    @GetMapping("/blog-single.html") //retorna o arquivo chamado index
    public String blogsingle(){
        return "blog-single";
    }

    @GetMapping("/shop-single.html") //retorna o arquivo chamado index
    public String shopsingle(){
        return "shop-single";
    }

    @GetMapping("/portfolio-category.html") //retorna o arquivo chamado index
    public String portfoliocategory(){
        return "portfolio-category";
    }

    @GetMapping("/contact.html") //retorna o arquivo chamado index
    public String contact(){
        return "contact";
    }



    @GetMapping("/pessoa")// retorna a entidade Pessoa como Json
    public ResponseEntity<Pessoa> getPessoa(){
        final Pessoa pessoa = new Pessoa("Bruno Abner", 22);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/bruno")// só retorna essa função quando a request for do tipo GET
    public ResponseEntity<Pessoa> getMenu(){
        final Pessoa pessoa = new Pessoa("Bruno Abner Comedor de Casada", 24);
        return ResponseEntity.ok(pessoa);
    }

    @RequestMapping("/minori") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public ResponseEntity<Pessoa> geMinoru(){
        final Pessoa pessoa = new Pessoa("Bruno Minori Rei Delas", 21);
        return ResponseEntity.ok(pessoa);
    }

}
