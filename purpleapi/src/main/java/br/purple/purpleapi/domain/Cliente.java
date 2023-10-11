package br.purple.purpleapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.purple.purpleapi.domain.enums.Perfil;
import br.purple.purpleapi.domain.enums.Pessoa;

@Entity
public class Cliente extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Produto> produto = new ArrayList<>();
	
	public Cliente () {
		super();
		addPerfil(Perfil.CLIENTE);
	}
	
	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}
	

}
