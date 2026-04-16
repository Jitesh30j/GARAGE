package com.garage.project.Owner.Repo;


import com.garage.project.Owner.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}