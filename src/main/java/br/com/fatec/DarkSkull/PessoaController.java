package br.com.fatec.DarkSkull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    @GetMapping("/pessoa")
    public ResponseEntity<Pessoa> getPessoa(){
        final Pessoa pessoa = new Pessoa("Bruno Abner", 14);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/menu")
    public ResponseEntity<Pessoa> getMenu(){
        final Pessoa pessoa = new Pessoa("Bruno Abner", 34);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/naruto") // só retorna essa função quando a request for do tipo GET
    public ResponseEntity<Pessoa> geNaruto(){
        final Pessoa pessoa = new Pessoa("Naruto", 105);
        return ResponseEntity.ok(pessoa);
    }

    @RequestMapping("/mario") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public ResponseEntity<Pessoa> geMario(){
        final Pessoa pessoa = new Pessoa("Mario Bros", 2020);
        return ResponseEntity.ok(pessoa);
    }

    @RequestMapping("/Luigi") // só retorna essa função quando a request for do tipo GET / PUT / POST / PATH
    public ResponseEntity<Pessoa> geLuigi(){
        final Pessoa pessoa = new Pessoa("Luigi Bros", 2021);
        return ResponseEntity.ok(pessoa);
    }

}
