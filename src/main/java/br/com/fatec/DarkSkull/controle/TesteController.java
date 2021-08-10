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
        return "index";
    }

    @GetMapping("/menu") //retorna o arquivo chamado menu
    public String menu(){
        return "menu";
    }

    @GetMapping("/cadastro") //retorna o arquivo chamado cadastrado
    public String cadastro(){
        return "cadastrado";
    }

    @GetMapping("/salvar")// só retorna essa função quando a request for do tipo GET
    public ResponseEntity<Pessoa> salvar(){
        final Pessoa pessoa = new Pessoa("Bruno Abner Comedor de Casada", 24);
        return ResponseEntity.ok(pessoa);
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
