package com.example.Ticketing.Controllers;

import com.example.Ticketing.Controllers.APIs.CSEAPI;
import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.RequestModel.CSERequestModel;
import com.example.Ticketing.Services.CSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController

public class CSEController implements CSEAPI {

    //Field Injection

    private CSEService cseService;

    //Getter Injection
    @Autowired
    public CSEService getCSEService() {
        return cseService;
    }

    @Autowired
    public CSEController(CSEService cseService) {
        this.cseService = cseService;
    }


    @Override
    public ResponseEntity<?> registerCSE(CSERequestModel cse) {
        return cseService.registerCSE(cse);
    }

    @Override
    public ResponseEntity<?> acceptCSE(CSE cse) {
        return cseService.acceptCse(cse);
    }

    @Override
    public void deleteCSE(Long id) {
        cseService.deleteCse(id);
    }

    @Override
    public CSE updateCSE(CSE cse) {
        return cseService.updateCse(cse);
    }

    @Override
    public Optional<CSE> findCSEById(Long id) {
        return cseService.findById(id);
    }

    @Override
    public List<CSE> findAllCSEs() {
        return cseService.findAll();
    }
}
