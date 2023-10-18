package br.purple.purpleapi.domain.enums;

import lombok.Getter;

@Getter
public enum VendasOp {
	ONLINE(0, "ONLINE"), LOJAFIS(1, "LOJAFIS"), AMBOS(2, "AMBOS");
	
	private Integer codigo;
	private String descricao;
	
	private VendasOp(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static VendasOp toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
	
	
	for(VendasOp x : VendasOp.values()) {
		if(cod.equals(x.getCodigo())) {
			return x;
		}
	}
	throw new IllegalArgumentException("Operação de venda inválida");
	}
}
