package br.purple.purpleapi.domain.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.purple.purpleapi.domain.ProdutoRegister;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRegDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	@NotNull(message = "O campo TITULO é requerido")
	private String titulo;
	@NotNull(message = "O campo DESCRIÇÃO é requerido")
	private String descricao;
	@NotNull(message = "O campo PREÇO é requerido")
	private BigDecimal preco;
	@NotNull(message = "O campo ESTOQUE é requerido")
	private Integer estoq;
	@NotNull(message = "O campo CATEGORIA é requerido")
	private Integer categoria;
	private String marca;
	private BigDecimal precoAnt;
	private String codigoProd;
	@NotNull(message = "O campo OPERADOR é requerido")
	private Integer operador;
	private String nomeOperador;
	private Integer vendasOp;

	public ProdutoRegDTO() {
		super();
	}

	public ProdutoRegDTO(ProdutoRegister obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.titulo = obj.getTitulo();
		this.descricao = obj.getDescricao();
		this.preco = obj.getPreco();
		this.estoq = obj.getEstoq();
		this.categoria = obj.getCategoria().getCodigo();
		this.marca = obj.getMarca();
		this.precoAnt = obj.getPrecoAnt();
		this.codigoProd = obj.getCodigoProd();
		this.operador = obj.getOperador().getId();
		this.nomeOperador = obj.getOperador().getNome();
	}

}
