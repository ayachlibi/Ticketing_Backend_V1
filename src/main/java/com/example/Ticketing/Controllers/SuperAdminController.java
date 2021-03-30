package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.API.SuperAdminAPI;
import com.example.Ticketing.Models.SuperAdmin;
import com.example.Ticketing.Service.AdminService;
import com.example.Ticketing.Service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class SuperAdminController implements SuperAdminAPI {
    //Field Injection

    private SuperAdminService superAdminService;

    //Getter Injection
    @Autowired
    public SuperAdminService getSuperAdminService(){
        return superAdminService;
    }

    //Constructor Injection
    @Autowired
    public SuperAdminController(SuperAdminService superAdminService){
        this.superAdminService=superAdminService;
    }

    @Override
    public SuperAdmin saveSuperAdmin(SuperAdmin superAdmin) {
        return superAdminService.addSuperAdmin(superAdmin);
    }

    @Override
    public void deleteSuperAdmin(Long id) {
        superAdminService.deleteSuperAdmin(id);
    }

    @Override
    public SuperAdmin updateSuperAdmin(SuperAdmin superAdmin) {
        return superAdminService.updateAdmin(superAdmin);
    }

    @Override
    public Optional<SuperAdmin> findSuperAdminById(Long id) {
        return superAdminService.findSuperAdminById(id);
    }

    @Override
    public List<SuperAdmin> findAllSuperAdmins() {
        return superAdminService.findAllSuperAdmins();
    }
}
