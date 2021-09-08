package br.com.fatec.DarkSkull.model.dominio.produto.calcas;

import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Short {

    private String nome;
    private long valor;
    private Tamanho tamanho;
    private int quantidade;
    private String descricao;



}
