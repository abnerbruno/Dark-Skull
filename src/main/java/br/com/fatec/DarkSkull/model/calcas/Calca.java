package br.com.fatec.DarkSkull.model.calcas;

import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Calca {

    private String nome;
    private long valor;
    private Tamanho tamanho;
    private int quantidade;
    private String descricao;



}
