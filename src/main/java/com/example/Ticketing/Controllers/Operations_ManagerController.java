package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.APIs.Operations_ManagerAPI;
import com.example.Ticketing.Models.Operations_Manager;
import com.example.Ticketing.Services.Operations_ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Operations_ManagerController implements Operations_ManagerAPI {

    //Field Injection

    private Operations_ManagerService operations_managerService;

    //Getter Injection
    @Autowired
    public Operations_ManagerService getOperations_managerService() {
        return operations_managerService;
    }

    @Autowired
    public Operations_ManagerController(Operations_ManagerService operations_managerService) {
        this.operations_managerService = operations_managerService;
    }


    @Override
    public Operations_Manager saveOperations_Manager(Operations_Manager op) {
        return operations_managerService.save(op);
    }

    @Override
    public void deleteOperations_Manager(Long id) {
        operations_managerService.delete(id);
    }

    @Override
    public Operations_Manager updateOperations_Manager(Operations_Manager op) {
        return operations_managerService.update(op);
    }

    @Override
    public Optional<Operations_Manager> findOperations_ManagerById(Long id) {
        return operations_managerService.findById(id);
    }

    @Override
    public List<Operations_Manager> findAllOperations_Managers() {
        return operations_managerService.findAll();
    }
}
