package br.com.criandoapi.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.criandoapi.projeto.domain.model.Users;

/**
 * manoel.carvalho
 *
 */
@Repository
public interface IUsersRepository extends JpaRepository<Users, Long> {

	public Users findBynomeOrEmail(String nome, String email);

}
