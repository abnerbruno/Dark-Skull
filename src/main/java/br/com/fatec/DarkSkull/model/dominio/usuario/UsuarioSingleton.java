package br.com.fatec.DarkSkull.model.dominio.usuario;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

public class UsuarioSingleton extends EntidadeDominio {

    protected String email;
    protected String senha;
    protected String status;


}
