package br.com.fatec.DarkSkull.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EntidadeDominio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String typeName = this.getClass().getSimpleName();
    protected final Timestamp dataCadastro = new Timestamp(System.currentTimeMillis());


}
