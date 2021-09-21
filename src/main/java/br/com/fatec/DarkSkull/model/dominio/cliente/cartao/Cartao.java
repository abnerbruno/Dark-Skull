package br.com.fatec.DarkSkull.model.dominio.cliente.cartao;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cartao")
public class Cartao extends EntidadeDominio {

    private String nome;
    private String numeroCartão;
    private String codSeguranca;
    private String bandeira;
    private String descricao;
}
