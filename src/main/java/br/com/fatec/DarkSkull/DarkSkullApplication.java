package br.com.fatec.DarkSkull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DarkSkullApplication {

	public static void main(String[] args) {


		ConfigurableApplicationContext configurableApplicationContext =
				SpringApplication.run(DarkSkullApplication.class, args);

		ClienteDao clienteDao = configurableApplicationContext.getBean(ClienteDao.class);
	}

}

//	DROP SCHEMA public CASCADE;
//		CREATE SCHEMA public;

//validate ao inves de create do arquivo aplication.properties para parar de sempre criar uma tabela