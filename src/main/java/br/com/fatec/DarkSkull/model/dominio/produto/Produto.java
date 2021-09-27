package br.com.fatec.DarkSkull.model.dominio.produto;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "produto")
public class Produto extends EntidadeDominio {

    private String nome;
    private long valor;
    private String tamanho;
    private int quantidade;
    private String descricao;

    private String imagem = "image/iimg/product-1.jpg";


}
