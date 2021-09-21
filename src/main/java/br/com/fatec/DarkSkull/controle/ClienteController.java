package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.repository.ClienteRepositorio;
import br.com.fatec.DarkSkull.repository.EnderecoRepositorio;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Cidade;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Endereco;
import br.com.fatec.DarkSkull.model.dominio.cliente.Cliente;
import br.com.fatec.DarkSkull.model.dominio.cliente.endereco.Estado;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import static br.com.fatec.DarkSkull.util.ComportamentoEndereco.*;

@AllArgsConstructor

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepositorio clienteRepositorio;
    private final EnderecoRepositorio enderecoRepositorio;

    @GetMapping()
    public ModelAndView buscarCliente(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("clientes/cliente");

        Optional<Cliente> Opcionalcliente = this.clienteRepositorio.findById(id);
        Cliente cliente = Opcionalcliente.get();
        //Buscar Cliente no Banco

        Endereco endPag = null;
        Endereco endEnv = null;
        for(Endereco end : cliente.getEnderecos()){
            if(end.getComportamento() == PAGAMENTO_E_ENVIO.getCode()){
                endPag = end;
                endEnv = end;
            } else if(end.getComportamento() == PAGAMENTO.getCode()){
                endPag = end;
            } else if(end.getComportamento() == ENVIO.getCode()){
                endEnv = end;
            }
        }
        // Buscar Lista de enterços no banco

        modelAndView.addObject("cliente", cliente);
        modelAndView.addObject("enderecos", cliente.getEnderecos());
        modelAndView.addObject("enderecopagamento", endPag);
        modelAndView.addObject("enderecoenvio", endEnv);


        return modelAndView;
    }

    @PostMapping("/atualizar_dados")
    public String atualizarDadosPessoais(@RequestParam Map<String,String> allParamsCliente) throws ParseException {

        Long id = Long.valueOf(allParamsCliente.get("id"));
        Optional<Cliente> opcionalCliente = this.clienteRepositorio.findById(id);

        String nome = allParamsCliente.get("nome");
        String email = allParamsCliente.get("usuario.email");
        String telefone = allParamsCliente.get("telefone");
        String cpf = allParamsCliente.get("cpf");
        String genero = allParamsCliente.get("genero");

        Cliente cliente = opcionalCliente.get();
        cliente.setNome(nome);
        cliente.getUsuario().setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setGenero(genero);

        this.clienteRepositorio.save(cliente);
        return "mensagens/alterado";
    }

    @PostMapping("/atualizar_senha")
    public String atualizarSenha(@RequestParam Map<String,String> allParamsCliente) {

        Long id = Long.valueOf(allParamsCliente.get("id"));
        Optional<Cliente> opcionalCliente = this.clienteRepositorio.findById(id);

        String senha = allParamsCliente.get("usuario.senha");
        String novasenha = allParamsCliente.get("novasenha");
        String confirmarnovasenha = allParamsCliente.get("confirmarnovasenha");


        Cliente cliente = opcionalCliente.get();
        cliente.getUsuario().setSenha(novasenha);

        this.clienteRepositorio.save(cliente);
        return "mensagens/alterado";
    }

    @GetMapping("/inativado")
    public String inativarCliente(@RequestParam("id") Long id) {
        Optional<Cliente> opcionalCliente = this.clienteRepositorio.findById(id);
        Cliente cliente = opcionalCliente.get();
        cliente.getUsuario().setStatus("Inativado");
        this.clienteRepositorio.save(cliente);
        return "mensagens/inativado";
    }

    @GetMapping("/novo_endereco")
    public ModelAndView telaNovoEndereco(@RequestParam("clienteid") Long clienteid) {

        ModelAndView modelAndView = new ModelAndView("clientes/novo_endereco");
        modelAndView.addObject("clienteid", clienteid);

        return modelAndView;
    }

    @PostMapping("/novo_endereco")
    public String salvarNovoEndereco(@RequestParam Map<String,String> allParamsEndereco,
                                     @RequestParam(value = "end_entrega", required = false) String isEndEntrega,
                                     @RequestParam(value = "end_pagamento", required = false) String isEndPagamento) {

        Estado estado = new Estado();
        estado.setNome(allParamsEndereco.get("estado"));

        Cidade cidade = new Cidade();
        cidade.setEstado(estado);
        cidade.setNome(allParamsEndereco.get("cidade"));
        cidade.setDescricao(allParamsEndereco.get("complemento"));
        cidade.setBairro(allParamsEndereco.get("bairro"));

        Endereco endereco = new Endereco();
        endereco.setCidade(cidade);
        endereco.setLongadouro(allParamsEndereco.get("logradouro"));
        endereco.setNumero(allParamsEndereco.get("numero"));
        endereco.setTipoResidencia(allParamsEndereco.get("tipoendereco"));
        endereco.setCep(allParamsEndereco.get("cep"));
        endereco.setDescricao(allParamsEndereco.get("descricao"));
        endereco.setClienteId(Long.valueOf(allParamsEndereco.get("clienteid")));

        salvarEnderecovalidandoTipoComportamento(endereco, endereco.getClienteId(), isEndEntrega, isEndPagamento);

        return "mensagens/inserido";
    }
    @GetMapping("/alterar_endereco")
    public ModelAndView AntesAlterarEndereco(@RequestParam("id") Long id) {

        Optional<Endereco> OpcionalEndereco = this.enderecoRepositorio.findById(id);
        Endereco endereco = OpcionalEndereco.get();

        ModelAndView modelAndView = new ModelAndView("clientes/alterar_endereco");
        modelAndView.addObject("endereco", endereco);

        return modelAndView;
    }


    @PostMapping("/alterar_endereco")
    public String alterarEndereco(@ModelAttribute("endereco") Endereco endereco,
                                               @RequestParam(value = "end_entrega", required = false) String isEndEntrega,
                                               @RequestParam(value = "end_pagamento", required = false) String isEndPagamento) {

        salvarEnderecovalidandoTipoComportamento(endereco, endereco.getClienteId(), isEndEntrega, isEndPagamento);

        return "mensagens/alterado";
    }

    @GetMapping("/excluir_endereco")
    public String excluirEndereco(@RequestParam("id") Long id) {
        this.enderecoRepositorio.deleteById(id);
        return "mensagens/excluido";
    }

    private void salvarEnderecovalidandoTipoComportamento(Endereco endereco, Long clienteid, String isEndEntrega, String isEndPagamento){

        Endereco enderecobancoPagamento = this.enderecoRepositorio.findByClienteIdAndComportamento(clienteid, PAGAMENTO.getCode());
        Endereco enderecobancoenvio = this.enderecoRepositorio.findByClienteIdAndComportamento(clienteid, ENVIO.getCode());
        Endereco endBancoPagamentoEnvio = this.enderecoRepositorio.findByClienteIdAndComportamento(clienteid, PAGAMENTO_E_ENVIO.getCode());

        if(isEndPagamento != null && isEndEntrega != null){
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

        } else if (isEndPagamento != null){
            if(enderecobancoPagamento != null){
                enderecobancoPagamento.setComportamento(PADRAO.getCode());
                this.enderecoRepositorio.save(enderecobancoPagamento);
            }

            endereco.setComportamento(PAGAMENTO.getCode());
            this.enderecoRepositorio.save(endereco);

        } else if (isEndEntrega != null){//"on"
            if(enderecobancoenvio != null){
                enderecobancoenvio.setComportamento(PADRAO.getCode());
                this.enderecoRepositorio.save(enderecobancoenvio);
            }

            endereco.setComportamento(ENVIO.getCode());
            this.enderecoRepositorio.save(endereco);
        } else {

            Endereco endDoBanco = this.enderecoRepositorio.findByid(endereco.getId());
            if(endDoBanco != null){
                endereco.setComportamento(endDoBanco.getComportamento());
            }else {
                endereco.setComportamento(PADRAO.getCode());
            }
            this.enderecoRepositorio.save(endereco);

        }
    }

    @RequestMapping("/alterar_cartao")
    public String alterarClienteCartao() {
        return "alterar_cartao";
    }

    @RequestMapping("/alterar_pedido")
    public String alterarClientePedido() {
        return "alterar_pedido";
    }

}
