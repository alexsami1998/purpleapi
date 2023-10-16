package br.purple.purpleapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.purple.purpleapi.domain.Cliente;
import br.purple.purpleapi.domain.Operador;
import br.purple.purpleapi.domain.Produto;
import br.purple.purpleapi.domain.dtos.ProdutoDTO;
import br.purple.purpleapi.domain.enums.Status;
import br.purple.purpleapi.repositories.ProdutoRepository;
import br.purple.purpleapi.service.exceptions.ObjectnotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private OperadorService operadorService;
	@Autowired
	private ClienteService clienteService;
	
	public Produto findById(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado. ID: " + id));
	}
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto create(ProdutoDTO obj) {
		return repository.save(newProduto(obj));
	}
	
	public Produto update(Integer id, @Valid ProdutoDTO objDTO) {
		objDTO.setId(id);
		Produto oldObj = findById(id);
		oldObj = newProduto(objDTO);
		return repository.save(oldObj);
	}
	
	private Produto newProduto(ProdutoDTO obj) {
		Operador operador = operadorService.findById(obj.getOperador());
		Cliente cliente = clienteService.findById(obj.getCliente());
		
		Produto produto = new Produto();
		if(obj.getId() != null) {
			produto.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			produto.setDataFechamento(LocalDate.now());
		}
		
		produto.setOperador(operador);
		produto.setCliente(cliente);
		produto.setStatus(Status.toEnum(obj.getStatus()));
		produto.setCategoria(obj.getCategoria());
		produto.setDescricao(obj.getDescricao());
		produto.setTitulo(obj.getTitulo());
		produto.setPreco(obj.getPreco());
		produto.setQntdEst(obj.getQntdEst());
		return produto;
	}

}
