/*package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.ApiResponse;
import com.garage.project.Owner.Dto.ServiceReminderDto;
import com.garage.project.Owner.Entity.*;
import com.garage.project.Owner.Repo.*;
import com.garage.project.Owner.Service.EmailService;
import com.garage.project.Owner.Service.ServiceReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceReminderServiceIMPL implements ServiceReminderService {

    @Autowired
    private ServiceReminderRepo reminderRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private GarageRepository garageRepo;

    @Autowired
    private EmailService emailService;

    // CREATE
    @Override
    public ApiResponse createReminder(ServiceReminderDto dto) {
        try {

            Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId()).orElseThrow();
            Customer customer = customerRepo.findById(dto.getCustomerId()).orElseThrow();
            Garage garage = garageRepo.findById(dto.getGarageId()).orElseThrow();

            ServiceReminder reminder = new ServiceReminder();
            reminder.setVehicle(vehicle);
            reminder.setCustomer(customer);
            reminder.setGarage(garage);

            reminder.setServiceType(dto.getServiceType());
            reminder.setLastServiceDate(dto.getLastServiceDate());
            reminder.setNextServiceDate(dto.getNextServiceDate());

            reminderRepo.save(reminder);

            return new ApiResponse ("success", 200, "Reminder created successfully", null);

        } catch (Exception e) {
            return new ApiResponse ("failure", 500, "Unable to create reminder", null);
        }
    }

    // UPDATE
    @Override
    public ApiResponse updateReminder(ServiceReminderDto dto) {
        try {

            Optional<ServiceReminder> optional = reminderRepo.findById(dto.getId());

            if (optional.isEmpty()) {
                return new ApiResponse ("failure", 404, "Reminder not found", null);
            }

            ServiceReminder reminder = optional.get();

            Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId()).orElseThrow();
            Customer customer = customerRepo.findById(dto.getCustomerId()).orElseThrow();
            Garage garage = garageRepo.findById(dto.getGarageId()).orElseThrow();

            reminder.setVehicle(vehicle);
            reminder.setCustomer(customer);
            reminder.setGarage(garage);

            reminder.setServiceType(dto.getServiceType());
            reminder.setLastServiceDate(dto.getLastServiceDate());
            reminder.setNextServiceDate(dto.getNextServiceDate());

            reminderRepo.save(reminder);

            return new ApiResponse ("success", 200, "Reminder updated successfully", null);

        } catch (Exception e) {
            return new ApiResponse ("failure", 500, "Unable to update reminder", null);
        }
    }

    // DELETE
    @Override
    public ApiResponse deleteReminder(Long id) {
        try {

            if (!reminderRepo.existsById(id)) {
                return new ApiResponse ("failure", 404, "Reminder not found", null);
            }

            reminderRepo.deleteById(id);

            return new ApiResponse ("success", 200, "Reminder deleted successfully", null);

        } catch (Exception e) {
            return new ApiResponse ("failure", 500, "Unable to delete reminder", null);
        }
    }

    // GET BY GARAGE (NO DATA)
    @Override
    public ApiResponse getByGarageId(Long garageId) {
        try {
            reminderRepo.findByGarage_Id(garageId);
            return new ApiResponse ("success", 200, "Reminders fetched successfully", null);
        } catch (Exception e) {
            return new ApiResponse ("failure", 500, "Error fetching reminders", null);
        }
    }

    // EMAIL + SCHEDULER
    @Override
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendReminders() {

        List<ServiceReminder> reminders = reminderRepo.findAll();
        Date today = new Date();

        for (ServiceReminder reminder : reminders) {

            if (reminder.getStatus() == 0 &&
                    reminder.getNextServiceDate() != null &&
                    reminder.getNextServiceDate().equals(today)) {

                Customer customer = reminder.getCustomer();

                if (customer != null && customer.getEmail() != null) {

                    String subject = "Service Reminder";
                    String message = "Hello " + customer.getName() +
                            "\nVehicle: " + reminder.getVehicle().getVehicleNumber() +
                            "\nService: " + reminder.getServiceType();

                    emailService.sendEmail(customer.getEmail(), subject, message);

                    reminder.setStatus(1);
                    reminderRepo.save(reminder);
                }
            }
        }
    }
}*/
package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.ApiResponse;
import com.garage.project.Owner.Dto.ServiceReminderDto;
import com.garage.project.Owner.Entity.*;
import com.garage.project.Owner.Repo.*;
import com.garage.project.Owner.Service.EmailService;
import com.garage.project.Owner.Service.ServiceReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceReminderServiceIMPL implements ServiceReminderService {

    @Autowired
    private ServiceReminderRepo reminderRepo;

    @Autowired
    private VehicleRepository vehicleRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private GarageRepository garageRepo;

    @Autowired
    private EmailService emailService;

    // ✅ CREATE
    @Override
    public ApiResponse createReminder(ServiceReminderDto dto) {
        try {
            Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId()).orElseThrow();
            Customer customer = customerRepo.findById(dto.getCustomerId()).orElseThrow();
            Garage garage = garageRepo.findById(dto.getGarageId()).orElseThrow();

            ServiceReminder reminder = new ServiceReminder();
            reminder.setVehicle(vehicle);
            reminder.setCustomer(customer);
            reminder.setGarage(garage);
            reminder.setServiceType(dto.getServiceType());
            reminder.setLastServiceDate(dto.getLastServiceDate());
            reminder.setNextServiceDate(dto.getNextServiceDate());

            reminderRepo.save(reminder);

            return new ApiResponse("success", 200, "Reminder created successfully", null);

        } catch (Exception e) {
            return new ApiResponse("failure", 500, "Unable to create reminder", null);
        }
    }

    // ✅ UPDATE
    @Override
    public ApiResponse updateReminder(ServiceReminderDto dto) {
        try {
            Optional<ServiceReminder> optional = reminderRepo.findById(dto.getId());

            if (optional.isEmpty()) {
                return new ApiResponse("failure", 404, "Reminder not found", null);
            }

            ServiceReminder reminder = optional.get();

            Vehicle vehicle = vehicleRepo.findById(dto.getVehicleId()).orElseThrow();
            Customer customer = customerRepo.findById(dto.getCustomerId()).orElseThrow();
            Garage garage = garageRepo.findById(dto.getGarageId()).orElseThrow();

            reminder.setVehicle(vehicle);
            reminder.setCustomer(customer);
            reminder.setGarage(garage);
            reminder.setServiceType(dto.getServiceType());
            reminder.setLastServiceDate(dto.getLastServiceDate());
            reminder.setNextServiceDate(dto.getNextServiceDate());

            reminderRepo.save(reminder);

            return new ApiResponse("success", 200, "Reminder updated successfully", null);

        } catch (Exception e) {
            return new ApiResponse("failure", 500, "Unable to update reminder", null);
        }
    }

    // ✅ DELETE
    @Override
    public ApiResponse deleteReminder(Long id) {
        try {
            if (!reminderRepo.existsById(id)) {
                return new ApiResponse("failure", 404, "Reminder not found", null);
            }

            reminderRepo.deleteById(id);

            return new ApiResponse("success", 200, "Reminder deleted successfully", null);

        } catch (Exception e) {
            return new ApiResponse("failure", 500, "Unable to delete reminder", null);
        }
    }

    // ✅ GET BY GARAGE
    @Override
    public ApiResponse getByGarageId(Long garageId) {
        List<ServiceReminder> list = reminderRepo.findByGarage_Id(garageId);

        return new ApiResponse("success", 200, "Reminders fetched successfully", list);
    }

    // ✅ EMAIL + SCHEDULER
    @Override
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendReminders() {

        List<ServiceReminder> reminders = reminderRepo.findAll();
        Date today = new Date();

        for (ServiceReminder reminder : reminders) {

            if (reminder.getStatus() == 0 &&
                    reminder.getNextServiceDate() != null &&
                    !reminder.getNextServiceDate().after(today)) {

                Customer customer = reminder.getCustomer();

                if (customer != null && customer.getEmail() != null) {

                    String subject = "Service Reminder";

                    String message =
                            "Hello " + customer.getName() + ",\n\n" +
                                    "Your vehicle (" + reminder.getVehicle().getVehicleNumber() + ") service is due.\n" +
                                    "Service Type: " + reminder.getServiceType() + "\n" +
                                    "Due Date: " + reminder.getNextServiceDate() + "\n\n" +
                                    "Please visit garage.\n\nThank you!";

                    emailService.sendEmail(customer.getEmail(), subject, message);

                    reminder.setStatus(1);
                    reminderRepo.save(reminder);
                }
            }
        }
    }
}