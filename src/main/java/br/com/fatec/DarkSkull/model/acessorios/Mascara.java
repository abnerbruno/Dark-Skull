package br.com.fatec.DarkSkull.model.acessorios;

import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Mascara {

    private String nome;
    private long valor;
    private Tamanho tamanho;
    private int quantidade;
    private String descricao;



}
