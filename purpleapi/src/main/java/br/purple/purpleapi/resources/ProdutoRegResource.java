package br.purple.purpleapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.purple.purpleapi.domain.ProdutoRegister;
import br.purple.purpleapi.domain.dtos.ProdutoRegDTO;
import br.purple.purpleapi.service.ProdutoRegService;

@RestController
@RequestMapping(value = "/produtosRegister")
public class ProdutoRegResource {
	
	@Autowired
	private ProdutoRegService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoRegDTO> findById(@PathVariable Integer id){
		ProdutoRegister obj = service.findById(id);
		return  ResponseEntity.ok().body(new ProdutoRegDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoRegDTO>> findAll() {
		List<ProdutoRegister> list = service.findAll();
		List<ProdutoRegDTO> listDTO = list.stream().map(obj -> new ProdutoRegDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoRegDTO> create(@Valid @RequestBody ProdutoRegDTO obj) {
		ProdutoRegister newObj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoRegDTO> update(@PathVariable Integer id, @Valid @RequestBody ProdutoRegDTO objDTO) {
		ProdutoRegister newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ProdutoRegDTO(newObj));
	}
	
	

}
