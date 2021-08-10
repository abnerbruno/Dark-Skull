package br.com.fatec.DarkSkull.model.calcas;

import br.com.fatec.DarkSkull.model.EndidadeDominio;
import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bermuda extends EndidadeDominio {

    private String nome;
    private Tamanho tamanho;
    private String descricao;
    private int idade; // bermuda para crinaças



}
