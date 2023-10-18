package br.purple.purpleapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.purple.purpleapi.domain.ProdutoRegister;

public interface ProdutoRegisterRepository extends JpaRepository<ProdutoRegister, Integer> {

}
