package br.purple.purpleapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.purple.purpleapi.domain.Operador;
import br.purple.purpleapi.domain.Pessoa;
import br.purple.purpleapi.domain.dtos.OperadorDTO;
import br.purple.purpleapi.repositories.OperadorRepository;
import br.purple.purpleapi.repositories.PessoaRepository;
import br.purple.purpleapi.service.exceptions.ObjectnotFoundException;

@Service
public class OperadorService {

	@Autowired
	private OperadorRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	public Operador findById(Integer id) {
		Optional<Operador> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Operador> findAll() {
		return repository.findAll();
	}
	
	public Operador create(OperadorDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Operador newObj = new Operador(objDTO);
		return repository.save(newObj);
	}
	
	public Operador update(Integer id, @Valid OperadorDTO objDTO) {
		objDTO.setId(id);
		Operador oldObj = findById(id);
		
		if(!objDTO.getSenha().equals(oldObj.getSenha())) 
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		
		validaPorCpfEEmail(objDTO);
		oldObj = new Operador(objDTO);
		return repository.save(oldObj);
	}
	
	public void delete(Integer id) {
		Operador obj = findById(id);
		
		if(obj.getProdutos().size() > 0) {
			throw new DataIntegrityViolationException("Operador possui produtos em aberto e não pode ser deletado.");
		}
	}
	
	private void validaPorCpfEEmail(OperadorDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF ja cadastrado no sistema");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail ja cadastrado no sistema");
		}
	}

}
