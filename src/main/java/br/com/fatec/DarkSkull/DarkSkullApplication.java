package br.com.fatec.DarkSkull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan //podemos dizer ao Spring onde procurar por essas classes anotadas
public class DarkSkullApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarkSkullApplication.class, args);
	}

}

//	DROP SCHEMA public CASCADE;
//		CREATE SCHEMA public;

//validate ao inves de create do arquivo aplication.properties para parar de sempre criar uma tabela