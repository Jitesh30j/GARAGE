package com.garage.project.Owner.ServiceIMPL;

import com.garage.project.Owner.Dto.RoleDTO;
import com.garage.project.Owner.Entity.Role;
import com.garage.project.Owner.Repo.RoleRepository;
import com.garage.project.Owner.Service.RoleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public void addRole(RoleDTO dto) {

        Role role = new Role();

        role.setName(dto.getName());
        role.setPermissions(dto.getPermissions());
        role.setStatus(1);

        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        role.setCreatedTimestamp(LocalDateTime.now());
        role.setUpdatedTimestamp(LocalDateTime.now());

        roleRepo.save(role);
    }
}