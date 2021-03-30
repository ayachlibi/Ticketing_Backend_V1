package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.API.AdminAPI;
import com.example.Ticketing.Models.Admin;
import com.example.Ticketing.Models.Client;
import com.example.Ticketing.Service.AdminService;
import com.example.Ticketing.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AdminController implements AdminAPI {
    //Field Injection

    private AdminService adminService;

    //Getter Injection
    @Autowired
    public AdminService getAdminService(){
        return adminService;
    }

    //Constructor Injection
    @Autowired
    public AdminController(AdminService adminService){
        this.adminService= adminService;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminService.addAdmin(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminService.deleteAdmin(id);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminService.updateAdmin(admin);
    }

    @Override
    public Optional<Admin> findAdminById(Long id) {
        return adminService.findAdminById(id);
    }

    @Override
    public List<Admin> findAllAdmins() {
        return adminService.findAllAdmins();
    }
}
