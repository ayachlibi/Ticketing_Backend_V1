package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.APIs.General_ManagerAPI;
import com.example.Ticketing.Models.General_Manager;
import com.example.Ticketing.Services.CSEService;
import com.example.Ticketing.Services.General_ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class General_ManagerController implements General_ManagerAPI {

    //Field Injection

    private General_ManagerService general_managerService;

    //Getter Injection
    @Autowired
    public General_ManagerService getGeneral_managerService() {
        return general_managerService;
    }

    @Autowired
    public General_ManagerController (General_ManagerService general_managerService) {
        this.general_managerService= general_managerService;
    }


    @Override
    public General_Manager saveGeneral_Manager(General_Manager gm) {
        return general_managerService.save(gm);
    }

    @Override
    public void deleteGeneral_Manager(Long id) {
        general_managerService.delete(id);
    }

    @Override
    public General_Manager updateGeneral_Manager(General_Manager gm) {
        return general_managerService.update(gm);
    }

    @Override
    public Optional<General_Manager> findGeneral_ManagerById(Long id) {
        return general_managerService.findById(id);
    }

    @Override
    public List<General_Manager> findAllGeneral_Managers() {
        return general_managerService.findAll();
    }
}
