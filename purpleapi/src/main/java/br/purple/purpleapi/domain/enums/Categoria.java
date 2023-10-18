package br.purple.purpleapi.domain.enums;

import lombok.Getter;

@Getter
public enum Categoria {
	ALIMENTOS(0, "ALIMENTOS"), BEBIDAS(1, "BEBIDAS"), PRODUTOS_DE_LIMPEZA(2, "PRODUTOS_DE_LIMPEZA"),
	HIGIENE(3, "HIGIENE"), ELETRONICOS(4, "ELETRONICOS"), VESTUARIO(5, "VESTUARIO"), BRINQUEDOS(6, "BRINQUEDOS"),
	OUTROS(7, "OUTROS");

	private Integer codigo;
	private String descricao;

	private Categoria(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static Categoria toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (Categoria x : Categoria.values()) {
			if (cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("A categoria não existe ou é inválida");
	}
}
