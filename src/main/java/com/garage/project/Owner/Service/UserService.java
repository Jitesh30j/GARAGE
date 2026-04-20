package com.garage.project.Owner.Service;

import com.garage.project.Owner.Dto.UserDTO;
import com.garage.project.Owner.Entity.User;

import java.util.List;

public interface UserService {

    void addUser(UserDTO dto);

    List<User> getAllUsers();

    User getUserById(int id);

    void deleteUser(int id);
}