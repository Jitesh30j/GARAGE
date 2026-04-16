package com.garage.project.Owner.ServiceIMPL;


import com.garage.project.Owner.Dto.UserDTO;
import com.garage.project.Owner.Entity.Garage;
import com.garage.project.Owner.Entity.Role;
import com.garage.project.Owner.Entity.User;
import com.garage.project.Owner.Repo.GarageRepository;
import com.garage.project.Owner.Repo.RoleRepository;
import com.garage.project.Owner.Repo.UserRepository;
import com.garage.project.Owner.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImpl implements UserService {

    private final UserRepository userRepo;
    private final GarageRepository garageRepo;
    private final RoleRepository roleRepo;

    public UserImpl(UserRepository userRepo,
                    GarageRepository garageRepo,
                    RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.garageRepo = garageRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public void addUser(UserDTO dto) {

        Garage garage = garageRepo.findById(dto.getGarageId())
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        Role role = roleRepo.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        user.setGarage(garage);
        user.setRole(role);

        userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    @Override
    public User getUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(int id) {
        userRepo.deleteById(id);
    }
}