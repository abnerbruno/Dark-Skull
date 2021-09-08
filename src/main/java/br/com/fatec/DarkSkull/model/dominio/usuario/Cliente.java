package br.com.fatec.DarkSkull.model.dominio.usuario;

import br.com.fatec.DarkSkull.model.dominio.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String nome;
    private String cpf;
    private Timestamp dataNascimento;
    private String genero;
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private List<Endereco> enderecoList;


    public Cliente(String email, String senha, String nome, String cpf, Timestamp dataNascimento, String genero, String telefone, Endereco endereco) {

        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setStatus("Ativado");

        this.usuario = usuario;

        this.enderecoList = new ArrayList<Endereco>();
        this.enderecoList.add(endereco);

    }

//    public Cliente(String nome, Timestamp dataNascimento, String email) {
//        this.nome = nome;
//        this.dataNascimento = dataNascimento;
//        this.email = email;
//    }
//
//    public Cliente(Long id, String nome, Timestamp timestamp, String email) {
//        this.id = id;
//        this.nome = nome;
//        this.dataNascimento = dataNascimento;
//        this.email = email;
//    }
//
//    public Cliente(Long id, String nome, String email) {
//        this.id = id;
//        this.nome = nome;
//        this.email = email;
//    }
}
