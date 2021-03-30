package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.API.CSEAPI;
import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Service.CSEService;
import com.example.Ticketing.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class CSEController implements CSEAPI {

    //Field Injection

    private CSEService cseService;

    //Getter Injection
    @Autowired
    public CSEService getCseService(){
        return cseService;
    }

    //Constructor Injection
    @Autowired
    public CSEController(CSEService cseService){
        this.cseService= cseService;
    }


    @Override
    public CSE saveCSE(CSE cse) {
        return cseService.addCSE(cse);
    }

    @Override
    public void deleteCSE(Long id) {
        cseService.deleteCSE(id);
    }

    @Override
    public CSE updateCSE(CSE cse) {
        return cseService.updateCSE(cse);
    }

    @Override
    public Optional<CSE> findCSEById(Long id) {
        return cseService.findCSEById(id);
    }

    @Override
    public List<CSE> findAllCSEs() {
        return cseService.findAllCSEs();
    }
}
