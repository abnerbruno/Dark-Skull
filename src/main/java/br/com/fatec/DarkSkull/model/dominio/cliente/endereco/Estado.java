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
@Table(name = "estado")
public class Estado extends EntidadeDominio {

    private String nome;
    private String descricao;

}
