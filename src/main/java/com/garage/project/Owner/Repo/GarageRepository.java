package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    Optional<Garage> findByEmail(String email);

    Optional<Garage> findByPhone(String phone);

    Optional<Garage> findByEmailOrPhone(String email, String phone);
}