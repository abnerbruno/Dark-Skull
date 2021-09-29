package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.model.dominio.cliente.cartao.Cartao;
import br.com.fatec.DarkSkull.repository.CartaoRepositorio;
import br.com.fatec.DarkSkull.util.constants;
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
public class CartaoDao implements IDAOEntidadeDominio {

    @Autowired
    private CartaoRepositorio cartaoRepositorio;


    @Override
    public List<Cartao> findAll() {
        return this.cartaoRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {

        Optional<Cartao> cartao = this.cartaoRepositorio.findById(id);
        return cartao.orElseGet(() -> this.cartaoRepositorio.findByClienteId(id));

    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        Cartao cartao = (Cartao) entidadeDominio;

        if(cartao.getComportamento() == constants.PRINCIPAL.getCode()){
            Optional<Cartao> cartaPrincipal = Optional.ofNullable(this.cartaoRepositorio.findByClienteIdAndComportamento(cartao.clienteId, constants.PRINCIPAL.getCode()));
            if(cartaPrincipal.isPresent()) {
                cartaPrincipal.get().setComportamento(constants.PADRAO.getCode());
                this.cartaoRepositorio.save(cartaPrincipal.get());
            }
        }

        return this.cartaoRepositorio.save((Cartao) entidadeDominio);
    }

    @Override
    public void deletedById(Long id) {
        this.cartaoRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.cartaoRepositorio.delete((Cartao) entidade);
    }

}
