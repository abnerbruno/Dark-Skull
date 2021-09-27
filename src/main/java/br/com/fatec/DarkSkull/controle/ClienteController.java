package br.com.fatec.DarkSkull.controle;

import br.com.fatec.DarkSkull.fachada.Fachada;
import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.cliente.cartao.Cartao;
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

import static br.com.fatec.DarkSkull.util.constants.*;

@AllArgsConstructor

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final Fachada fachada;

    @GetMapping()
    public ModelAndView buscarCliente(@RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("clientes/cliente");

        EntidadeDominio entidadeDominio = fachada.consultarbyId(Cliente.class.getName(), id);
        Cliente cliente = (Cliente) entidadeDominio;
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

        Optional<Cartao> cartaoPrincipal = Optional.empty();
        for(Cartao cartao : cliente.getCartoes()){
            if(cartao.getComportamento() == PRINCIPAL.getCode()){
                cartaoPrincipal = Optional.of(cartao);
            }
        }

        modelAndView.addObject("cliente", cliente);
        modelAndView.addObject("enderecos", cliente.getEnderecos());
        modelAndView.addObject("enderecopagamento", endPag);
        modelAndView.addObject("enderecoenvio", endEnv);
        modelAndView.addObject("cartaoprincipal", cartaoPrincipal);
        modelAndView.addObject("cartoes", cliente.getCartoes());


        return modelAndView;
    }

    @PostMapping("/atualizar_dados")
    public String atualizarDadosPessoais(@RequestParam Map<String,String> allParamsCliente) throws ParseException {

        Long id = Long.valueOf(allParamsCliente.get("id"));
        EntidadeDominio entidadeDominio = fachada.consultarbyId(Cliente.class.getName(), id);
        Cliente cliente = (Cliente) entidadeDominio;

        String nome = allParamsCliente.get("nome");
        String email = allParamsCliente.get("usuario.email");
        String telefone = allParamsCliente.get("telefone");
        String cpf = allParamsCliente.get("cpf");
        String genero = allParamsCliente.get("genero");

        cliente.setNome(nome);
        cliente.getUsuario().setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCpf(cpf);
        cliente.setGenero(genero);

        fachada.alterar(cliente);
        return "mensagens/alterado";
    }

    @PostMapping("/atualizar_senha")
    public String atualizarSenha(@RequestParam Map<String,String> allParamsCliente) {

        Long id = Long.valueOf(allParamsCliente.get("id"));
        EntidadeDominio entidadeDominio = fachada.consultarbyId(Cliente.class.getName(), id);
        Cliente cliente = (Cliente) entidadeDominio;

        String senha = allParamsCliente.get("usuario.senha");
        String novasenha = allParamsCliente.get("novasenha");
//        String confirmarnovasenha = allParamsCliente.get("confirmarnovasenha");


        cliente.getUsuario().setSenha(novasenha);

        fachada.salvar(cliente);
        return "mensagens/alterado";
    }

    @GetMapping("/inativado")
    public String inativarCliente(@RequestParam("id") Long id) {
        EntidadeDominio entidadeDominio = fachada.consultarbyId(Cliente.class.getName(), id);
        Cliente cliente = (Cliente) entidadeDominio;
        cliente.getUsuario().setStatus("Inativado");
        fachada.salvar(cliente);
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

        if(isEndEntrega != null && isEndPagamento != null){
            endereco.setComportamento(PAGAMENTO_E_ENVIO.getCode());
        } else if (isEndEntrega != null){
            endereco.setComportamento(ENVIO.getCode());
        } else if (isEndPagamento != null){
            endereco.setComportamento(PAGAMENTO.getCode());
        }

        fachada.salvar(endereco);

        return "mensagens/inserido";
    }
    @GetMapping("/alterar_endereco")
    public ModelAndView antesAlterarEndereco(@RequestParam("id") Long id) {

        EntidadeDominio entidadeDominio = fachada.consultarbyId(Endereco.class.getName(), id);
        Endereco endereco = (Endereco) entidadeDominio;

        ModelAndView modelAndView = new ModelAndView("clientes/alterar_endereco");
        modelAndView.addObject("endereco", endereco);

        return modelAndView;
    }


    @PostMapping("/alterar_endereco")
    public String alterarEndereco(@ModelAttribute("endereco") Endereco endereco,
                                               @RequestParam(value = "end_entrega", required = false) String isEndEntrega,
                                               @RequestParam(value = "end_pagamento", required = false) String isEndPagamento) {

        if(isEndEntrega != null && isEndPagamento != null){
            endereco.setComportamento(PAGAMENTO_E_ENVIO.getCode());
        } else if (isEndEntrega != null){
            endereco.setComportamento(ENVIO.getCode());
        } else if (isEndPagamento != null){
            endereco.setComportamento(PAGAMENTO.getCode());
        }

        fachada.salvar(endereco);

        return "mensagens/alterado";
    }

    @GetMapping("/excluir_endereco")
    public String excluirEndereco(@RequestParam("id") Long id) {
        fachada.excluirById(Endereco.class.getName(), id);
        return "mensagens/excluido";
    }


    @PostMapping("/novo_cartao")
    public String novoClienteCartao(@RequestParam Map<String,String> allParamsCartao) {
        Cartao cartao = new Cartao();
        cartao.setNome(allParamsCartao.get("nome"));
        cartao.setNumeroCartao(allParamsCartao.get("numeroCartao"));
        cartao.setCodSeguranca(allParamsCartao.get("codSeguranca"));
        cartao.setBandeira(allParamsCartao.get("bandeira"));
        cartao.setClienteId(Long.valueOf(allParamsCartao.get("id")));

        fachada.salvar(cartao);

        return "mensagens/inserido";
    }

    @GetMapping("/alterar_cartao")
    public ModelAndView antesClienteCartao(@RequestParam("id") Long id) {

        EntidadeDominio entidadeDominio = fachada.consultarbyId(Cartao.class.getName(), id);
        Cartao cartao = (Cartao) entidadeDominio;

        ModelAndView modelAndView = new ModelAndView("clientes/alterar_cartao");
        modelAndView.addObject("cartao", cartao);

        return modelAndView;
    }

    @PostMapping("/alterar_cartao")
    public String alterarCartao(@ModelAttribute("cartao") Cartao cartao,
                                      @RequestParam(value = "cartaopagamento", required = false) String isCartaoPricipal) {

        if(isCartaoPricipal != null){
            cartao.setComportamento(PRINCIPAL.getCode());
        }
        fachada.salvar(cartao);

        return "mensagens/alterado";
    }

    @GetMapping("/excluir_cartao")
    public String excluirCartao(@RequestParam("id") Long id) {
        fachada.excluirById(Cartao.class.getName(), id);
        return "mensagens/excluido";
    }

    @RequestMapping("/alterar_pedido")
    public String alterarClientePedido() {
        return "alterar_pedido";
    }

}
