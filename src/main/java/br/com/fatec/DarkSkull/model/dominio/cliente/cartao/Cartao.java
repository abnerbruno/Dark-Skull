package br.com.fatec.DarkSkull.model.dominio.cliente.cartao;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.util.constants;
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
    private String numeroCartao;
    private String codSeguranca;
    private String bandeira;
    private int comportamento = constants.PADRAO.getCode();

    @Column(name = "cliente_id")
    public Long clienteId;
}
