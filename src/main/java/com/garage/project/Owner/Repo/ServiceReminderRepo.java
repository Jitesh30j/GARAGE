package com.garage.project.Owner.Repo;

import com.garage.project.Owner.Entity.ServiceReminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceReminderRepo extends JpaRepository<ServiceReminder, Long> {

    List<ServiceReminder> findByGarage_Id(Long garageId);
}