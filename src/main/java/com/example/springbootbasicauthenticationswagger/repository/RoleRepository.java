package com.example.springbootbasicauthenticationswagger.repository;

import com.example.springbootbasicauthenticationswagger.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
