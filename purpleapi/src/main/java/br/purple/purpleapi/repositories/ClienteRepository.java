package br.purple.purpleapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.purple.purpleapi.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
