package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.VehicleDTO;
import java.util.List;

public interface VehicleService {

    void save(VehicleDTO dto);

    List<VehicleDTO> getAllByGarage(Long garageId);

    VehicleDTO getByIdAndGarage(Long vehicleId, Long garageId);

    void update(Long vehicleId, Long garageId, VehicleDTO dto);

    void delete(Long vehicleId, Long garageId);
}