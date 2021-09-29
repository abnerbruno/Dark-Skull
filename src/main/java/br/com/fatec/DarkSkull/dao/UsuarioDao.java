package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import br.com.fatec.DarkSkull.repository.UsuarioRepositorio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Getter
@Setter

@NoArgsConstructor
@Service
public class UsuarioDao implements IDAOEntidadeDominio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Override
    public List<Usuario> findAll() {
        return this.usuarioRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {

        Optional<Usuario> usuario = this.usuarioRepositorio.findById(id);
        EntidadeDominio entidade = usuario.get();

        return entidade;
    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        Usuario usuario = (Usuario) entidade;
        if(entidade.getId() == null){
           return this.usuarioRepositorio.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
        }
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        return this.usuarioRepositorio.save((Usuario) entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.usuarioRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.usuarioRepositorio.delete((Usuario) entidade);
    }

}
