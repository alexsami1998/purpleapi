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

import br.purple.purpleapi.domain.enums.Categoria;
import br.purple.purpleapi.domain.enums.VendasOp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ProdutoRegister implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;

	// informacoes gerais sobre produto
	private String titulo;
	private String descricao;
	private BigDecimal preco;
	private Integer estoq;
	private Categoria categoria;
	private String marca;
	private BigDecimal precoAnt;
	private String codigoProd;
	// lista de strings para imagens ->
	// imagens
	// especificacoes tecnicas
	private BigDecimal peso;
	// gerenciamento de estoque
	private Integer estoqIni; // quantidade inicial de estoque
	private Integer estoqMin; // limite minimo de estoque
	private Boolean estoqBaix; // Notificacao de estoque baixo -> reposicao
	// opcoes de venda
	private VendasOp vendasOp; 
	private String obs;

	@ManyToOne
	@JoinColumn(name = "operador_id")
	private Operador operador;

	public ProdutoRegister() {
		super();
	}

	public ProdutoRegister(Integer id, String titulo, String descricao, BigDecimal preco, Integer estoq,
			Categoria categoria, String marca, BigDecimal precoAnt, String codigoProd, BigDecimal peso, Integer estoqIni,
			Integer estoqMin, Boolean estoqBaix, VendasOp vendasOp, String obs, Operador operador) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.preco = preco;
		this.estoq = estoq;
		this.categoria = categoria;
		this.marca = marca;
		this.precoAnt = precoAnt;
		this.codigoProd = codigoProd;
		this.peso = peso;
		this.estoqIni = estoqIni;
		this.estoqMin = estoqMin;
		this.estoqBaix = estoqBaix;
		this.vendasOp = vendasOp;
		this.obs = obs;
		this.operador = operador;
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
		ProdutoRegister other = (ProdutoRegister) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
