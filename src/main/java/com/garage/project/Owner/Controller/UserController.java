package com.garage.project.Owner.Controller;
import com.garage.project.Owner.Dto.UserDTO;
import com.garage.project.Owner.Entity.User;
import com.garage.project.Owner.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addUser(@RequestBody UserDTO dto) {
        userService.addUser(dto);
        return "User created successfully";
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}