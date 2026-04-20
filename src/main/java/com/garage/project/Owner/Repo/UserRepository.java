package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);
}