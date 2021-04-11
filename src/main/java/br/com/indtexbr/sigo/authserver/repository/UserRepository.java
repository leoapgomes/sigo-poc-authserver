package br.com.indtexbr.sigo.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.indtexbr.sigo.authserver.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsernameAndDisabled(String username, boolean disabled);
}