package br.com.mickaelsantos.gestaovagasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br")
public class GestaoVagasApiApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(GestaoVagasApiApplication.class, args);
	}

}


