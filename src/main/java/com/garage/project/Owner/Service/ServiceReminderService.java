package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.ApiResponse;
import com.garage.project.Owner.Dto.ServiceReminderDto;

public interface ServiceReminderService {

    ApiResponse createReminder(ServiceReminderDto dto);

    ApiResponse updateReminder(ServiceReminderDto dto);

    ApiResponse deleteReminder(Long id);

    ApiResponse getByGarageId(Long garageId);

    void sendReminders();
}