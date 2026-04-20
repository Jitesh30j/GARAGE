/*package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.ApiResponse;
import com.garage.project.Owner.Dto.ServiceReminderDto;
import com.garage.project.Owner.Service.ServiceReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/reminder")
public class ServiceReminderController {

    @Autowired
    private ServiceReminderService reminderService;

    @PostMapping("/create")
    public ApiResponse create(@RequestBody ServiceReminderDto dto) {
        return reminderService.createReminder(dto);
    }

    @PutMapping("/update")
    public ApiResponse  update(@RequestBody ServiceReminderDto dto) {
        return reminderService.updateReminder(dto);
    }

    @DeleteMapping("/delete")
    public ApiResponse deleteByBody(@RequestBody ServiceReminderDto dto) {

        // ✅ Validation (IMPORTANT)
        if (dto.getId() == null) {
            return new ApiResponse("error", 400, "Id is required", null);
        }

        return reminderService.deleteReminder(dto.getId());
    }

    @PostMapping("/garage")
    public ApiResponse  getByGarage(@RequestBody ServiceReminderDto dto) {
        return reminderService.getByGarageId(dto.getGarageId());
    }
    @PostMapping("/send")   // ✅ change from GET to POST
    public ApiResponse sendReminder() {
        reminderService.sendReminders();
        return new ApiResponse("success", 200, "Reminder sent successfully", null);
    }
}*/
package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.ApiResponse;
import com.garage.project.Owner.Dto.ServiceReminderDto;
import com.garage.project.Owner.Service.ServiceReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/reminder")
public class ServiceReminderController {

    @Autowired
    private ServiceReminderService reminderService;

    // ✅ CREATE
    @PostMapping("/create")
    public ApiResponse create(@RequestBody ServiceReminderDto dto) {
        return reminderService.createReminder(dto);
    }

    // ✅ UPDATE (changed from PUT → POST)
    @PostMapping("/update")
    public ApiResponse update(@RequestBody ServiceReminderDto dto) {
        return reminderService.updateReminder(dto);
    }

    // ✅ DELETE (changed from DELETE → POST)
    @PostMapping("/delete")
    public ApiResponse delete(@RequestBody ServiceReminderDto dto) {

        if (dto.getId() == null) {
            return new ApiResponse("error", 400, "Id is required", null);
        }

        return reminderService.deleteReminder(dto.getId());
    }

    // ✅ GET BY GARAGE (already POST)
    @PostMapping("/garage")
    public ApiResponse getByGarage(@RequestBody ServiceReminderDto dto) {
        return reminderService.getByGarageId(dto.getGarageId());
    }

    // ✅ SEND REMINDER
    @PostMapping("/send")
    public ApiResponse sendReminder() {
        reminderService.sendReminders();
        return new ApiResponse("success", 200, "Reminder sent successfully", null);
    }
}