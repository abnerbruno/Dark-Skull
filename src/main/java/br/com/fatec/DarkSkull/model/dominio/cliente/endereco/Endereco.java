package br.com.fatec.DarkSkull.model.dominio.cliente.endereco;

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
@Table(name = "endereco")
public class Endereco extends EntidadeDominio {

    private String descricao;
    private String longadouro;
    private String numero;
    private String cep;
    private String tipoResidencia;
    private int comportamento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    public Cidade cidade;

    @Column(name = "cliente_id")
    public Long clienteId;

}
