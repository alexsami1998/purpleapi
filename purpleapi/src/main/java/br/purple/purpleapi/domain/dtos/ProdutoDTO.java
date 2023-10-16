package br.purple.purpleapi.domain.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.purple.purpleapi.domain.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	@NotNull(message = "O campo STATUS é requerido")
	private Integer status;
	@NotNull(message = "O campo TITULO é requerido")
	private String titulo;
	@NotNull(message = "O campo DESCRICAO é requerido")
	private String descricao;
	@NotNull(message = "O campo PREÇO é requerido")
	private BigDecimal preco;
	@NotNull(message = "O campo QUANTIDADE ESTQ. é requerido")
	private Integer qntdEst;
	@NotNull(message = "O campo CATEGORIA é requerido")
	private String categoria;
	@NotNull(message = "O campo CLIENTE é requerido")
	private Integer cliente;
	@NotNull(message = "O campo OPERADOR é requerido")
	private Integer operador;
	private String nomeOperador;
	private String nomeCliente;
	
	public ProdutoDTO() {
		super();
	}
	
	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.status = obj.getStatus().getCodigo();
		this.titulo = obj.getTitulo();
		this.descricao = obj.getDescricao();
		this.preco = obj.getPreco();
		this.qntdEst = obj.getQntdEst();
		this.categoria = obj.getCategoria();
		this.operador = obj.getOperador().getId();
		this.cliente = obj.getCliente().getId();
		this.nomeOperador = obj.getOperador().getNome();
		this.nomeCliente = obj.getCliente().getNome();
	}

}
