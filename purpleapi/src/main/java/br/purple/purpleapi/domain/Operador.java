package br.purple.purpleapi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.purple.purpleapi.domain.enums.Perfil;
import br.purple.purpleapi.domain.enums.Pessoa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Operador extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "operador")
	private List<Produto> produtos = new ArrayList<>();
	
	public Operador() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
