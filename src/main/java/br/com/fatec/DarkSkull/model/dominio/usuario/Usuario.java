package br.com.fatec.DarkSkull.model.dominio.usuario;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends EntidadeDominio {


    protected String email;
    protected String senha;
    protected String status;


}
