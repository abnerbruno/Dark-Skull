package br.com.fatec.DarkSkull.model.dominio.cliente;

import br.com.fatec.DarkSkull.model.dominio.cliente.cartao.Cartao;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import br.com.fatec.DarkSkull.util.ComportamentoEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartao_id", referencedColumnName = "id")
    private List<Cartao> cartoesList;


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


}