package br.purple.purpleapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.purple.purpleapi.domain.Operador;
import br.purple.purpleapi.domain.dtos.OperadorDTO;
import br.purple.purpleapi.service.OperadorService;

@RestController
@RequestMapping(value = "/operadores")
public class OperadorResource {
	
	@Autowired
	private OperadorService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OperadorDTO> findById(@PathVariable Integer id) {
		Operador obj = service.findById(id);
		return ResponseEntity.ok().body(new OperadorDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<OperadorDTO>> findAll() {
		List<Operador> list = service.findAll();
		List<OperadorDTO> listDTO = list.stream().map(obj -> new OperadorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<OperadorDTO> create(@Valid @RequestBody OperadorDTO objDTO) {
		Operador newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<OperadorDTO> update(@PathVariable Integer id, @Valid @RequestBody OperadorDTO objDTO) {
		Operador obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new OperadorDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<OperadorDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
