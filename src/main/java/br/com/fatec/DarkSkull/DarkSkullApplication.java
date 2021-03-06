package br.com.fatec.DarkSkull;

import br.com.fatec.DarkSkull.model.dominio.usuario.UsuarioSingleton;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

@SpringBootApplication
public class DarkSkullApplication {


	@Bean
	@SessionScope
	public UsuarioSingleton UsuarioSingleton() {
		return new UsuarioSingleton();
	}


	public static void main(String[] args) {
		SpringApplication.run(DarkSkullApplication.class, args);
	}

}


//	DROP SCHEMA public CASCADE;
//		CREATE SCHEMA public;

//validate ao inves de create do arquivo aplication.properties para parar de sempre criar uma tabela