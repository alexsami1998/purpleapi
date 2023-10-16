package br.purple.purpleapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.purple.purpleapi.domain.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;

	private Status status;
	private String titulo;
	private String descricao;
	private BigDecimal preco;
	private Integer qntdEst;
	private String categoria;

	@ManyToOne
	@JoinColumn(name = "operador_id")
	private Operador operador;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Produto() {
		super();
	}
	
	public Produto(Integer id, Status status, String titulo, String descricao, BigDecimal preco, Integer qntdEst,
			String categoria, Operador operador, Cliente cliente) {
		super();
		this.id = id;
		this.status = status;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.qntdEst = qntdEst;
		this.categoria = categoria;
		this.operador = operador;
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
