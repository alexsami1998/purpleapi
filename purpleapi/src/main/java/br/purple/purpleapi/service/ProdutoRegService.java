package br.purple.purpleapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.purple.purpleapi.domain.Operador;
import br.purple.purpleapi.domain.ProdutoRegister;
import br.purple.purpleapi.domain.dtos.ProdutoRegDTO;
import br.purple.purpleapi.domain.enums.Categoria;
import br.purple.purpleapi.repositories.ProdutoRegisterRepository;
import br.purple.purpleapi.service.exceptions.ObjectnotFoundException;

@Service
public class ProdutoRegService {

	@Autowired
	private ProdutoRegisterRepository repository;
	@Autowired
	private OperadorService operadorService;
	
	public ProdutoRegister findById(Integer id) {
		Optional<ProdutoRegister> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. ID: " + id));
	}
	
	public List<ProdutoRegister> findAll() {
		return repository.findAll();
	}
	
	public ProdutoRegister create(ProdutoRegDTO obj) {
		return repository.save(newProdutoRegister(obj));
	}
	
	public ProdutoRegister update(Integer id, @Valid ProdutoRegDTO objDTO) {
		objDTO.setId(id);
		ProdutoRegister oldObj = findById(id);
		oldObj = newProdutoRegister(objDTO);
		return repository.save(oldObj);
	}
	
	private ProdutoRegister newProdutoRegister(ProdutoRegDTO obj) {
		Operador operador = operadorService.findById(obj.getOperador());
		
		ProdutoRegister produtoRegister = new ProdutoRegister();
		if(obj.getId() != null) {
			produtoRegister.setId(obj.getId());
		}
		
		produtoRegister.setTitulo(obj.getTitulo());
		produtoRegister.setDescricao(obj.getDescricao());
		produtoRegister.setPreco(obj.getPreco());
		produtoRegister.setEstoq(obj.getEstoq());
		produtoRegister.setCategoria(Categoria.toEnum(obj.getCategoria()));
		produtoRegister.setMarca(obj.getMarca());
		produtoRegister.setPrecoAnt(obj.getPrecoAnt());
		produtoRegister.setCodigoProd(obj.getCodigoProd());
		produtoRegister.setOperador(operador);
		return produtoRegister;
		
	}
}
