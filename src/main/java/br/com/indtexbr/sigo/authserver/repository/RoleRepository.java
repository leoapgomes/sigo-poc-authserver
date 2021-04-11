package br.com.indtexbr.sigo.authserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.indtexbr.sigo.authserver.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}