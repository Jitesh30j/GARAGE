package com.garage.project.Owner.Controller;

import com.garage.project.Owner.Dto.RoleDTO;
import com.garage.project.Owner.Service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public Map<String, Object> addRole(@RequestBody RoleDTO dto) {

        roleService.addRole(dto);

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put("code", 200);
        res.put("message", "Role added successfully");
        res.put("data", " ");

        return res;
    }
}