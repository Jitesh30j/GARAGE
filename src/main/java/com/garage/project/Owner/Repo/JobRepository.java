package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByGarageId(Integer garageId);

    Optional<Job> findByJobIdAndGarageId(Integer jobId, Integer garageId);
}