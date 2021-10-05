package br.com.fatec.DarkSkull.model.dominio.cliente;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.cartao.Cartao;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {

    private String nome;
    private String cpf;

    @CreationTimestamp
    private Date dataNascimento;

    private String genero;
    private String telefone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Set<Endereco> enderecos;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Set<Cartao> cartoes;


    public Cliente(String email, String senha, String nome, String cpf, Date dataNascimento, String genero, String telefone, Endereco endereco) {

        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;

        super.setEmail(email);
        super.setSenha(senha);
        super.setStatus("Ativado");

        this.enderecos = new HashSet<Endereco>();
        this.enderecos.add(endereco);

    }


}
