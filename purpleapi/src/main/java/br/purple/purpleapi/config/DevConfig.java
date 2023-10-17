package br.purple.purpleapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.purple.purpleapi.service.DBService;

@Configuration
public class ConfigConex {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.dll-auto}")
	private String value;
	
	@Bean
	public boolean instanciaDB() {
		if(value.equals("create")) {
			this.dbService.instanciaDB();
		}
		
		return false;
	}

}
