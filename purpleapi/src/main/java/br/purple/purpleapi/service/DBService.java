package br.purple.purpleapi.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.purple.purpleapi.domain.Operador;
import br.purple.purpleapi.domain.enums.Perfil;
import br.purple.purpleapi.repositories.PessoaRepository;

@Service
public class DBService {
	
	//@Autowired
	//private Produto produto;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instanciaDB() {
		
		Operador op1 = new Operador(null, "Alexandre Magno", "03969987652", "alexandre@mail.com", encoder.encode("123456789"));
		op1.addPerfil(Perfil.ADMIN);
		Operador op2 = new Operador(null, "Linus torvalds", "03669875612", "linus@mail.com", encoder.encode("123456789"));
		
		pessoaRepository.saveAll(Arrays.asList(op1, op2));
	}
}