package br.purple.purpleapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.purple.purpleapi.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}