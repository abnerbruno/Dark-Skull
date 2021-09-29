package br.com.fatec.DarkSkull.dao;


import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import br.com.fatec.DarkSkull.repository.EnderecoRepositorio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.fatec.DarkSkull.util.constants.*;
import static br.com.fatec.DarkSkull.util.constants.PADRAO;

@Getter
@Setter

@NoArgsConstructor
@Service
public class EnderecoDao implements IDAOEntidadeDominio {

    @Autowired
    private EnderecoRepositorio enderecoRepositorio;


    @Override
    public List<Endereco> findAll() {
        return this.enderecoRepositorio.findAll();
    }

    @Override
    public EntidadeDominio getById(Long id) {

        Optional<Endereco> endereco = this.enderecoRepositorio.findById(id);
        EntidadeDominio entidade = endereco.get();

        return entidade;
    }

    @Override
    public EntidadeDominio getEntidade(EntidadeDominio entidade) {
        return null;
    }

    @Override
    public EntidadeDominio saveOrUpdate(EntidadeDominio entidadeDominio) {
        Endereco endereco = (Endereco) entidadeDominio;

        if(endereco.getClienteId() == null){
            return this.enderecoRepositorio.save((Endereco) entidadeDominio);
        } else {
            salvarEnderecovalidandoTipoComportamento(endereco);
        }

        return entidadeDominio;
    }

    @Override
    public void deletedById(Long id) {
        this.enderecoRepositorio.deleteById(id);
    }

    @Override
    public void deleted(EntidadeDominio entidade) {
        this.enderecoRepositorio.delete((br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco) entidade);
    }

    public void salvarEnderecovalidandoTipoComportamento(Endereco endereco) {

        Endereco enderecobancoPagamento = this.enderecoRepositorio.findByClienteIdAndComportamento(endereco.getClienteId(), PAGAMENTO.getCode());
        Endereco enderecobancoenvio = this.enderecoRepositorio.findByClienteIdAndComportamento(endereco.getClienteId(), ENVIO.getCode());
        Endereco endBancoPagamentoEnvio = this.enderecoRepositorio.findByClienteIdAndComportamento(endereco.getClienteId(), PAGAMENTO_E_ENVIO.getCode());

        if(endereco.getComportamento() == PAGAMENTO_E_ENVIO.getCode()){
            if(enderecobancoPagamento != null){
                enderecobancoPagamento.setComportamento(PADRAO.getCode());
                this.enderecoRepositorio.save(enderecobancoPagamento);
            }

            if(enderecobancoenvio != null){
                enderecobancoenvio.setComportamento(PADRAO.getCode());
                this.enderecoRepositorio.save(enderecobancoenvio);
            }

            if(endBancoPagamentoEnvio != null){
                endBancoPagamentoEnvio.setComportamento(PADRAO.getCode());
                this.enderecoRepositorio.save(endBancoPagamentoEnvio);
            }


            endereco.setComportamento(PAGAMENTO_E_ENVIO.getCode());
            this.enderecoRepositorio.save(endereco);

        } else if (endereco.getComportamento() == PAGAMENTO.getCode()){
            if(enderecobancoPagamento != null){
                enderecobancoPagamento.setComportamento(PADRAO.getCode());
                this.enderecoRepositorio.save(enderecobancoPagamento);
            }

            endereco.setComportamento(PAGAMENTO.getCode());
            this.enderecoRepositorio.save(endereco);

        } else if (endereco.getComportamento() == ENVIO.getCode()){
            if(enderecobancoenvio != null){
                enderecobancoenvio.setComportamento(PADRAO.getCode());
                this.enderecoRepositorio.save(enderecobancoenvio);
            }

            endereco.setComportamento(ENVIO.getCode());
            this.enderecoRepositorio.save(endereco);
        } else {

            Endereco endDoBanco = this.enderecoRepositorio.findByid(endereco.getId());
            if (endDoBanco != null) {
                endereco.setComportamento(endDoBanco.getComportamento());
            } else {
                endereco.setComportamento(PADRAO.getCode());
            }
            this.enderecoRepositorio.save(endereco);

        }

    }
}
