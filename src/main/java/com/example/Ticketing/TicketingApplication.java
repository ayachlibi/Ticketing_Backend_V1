package com.example.Ticketing;

import com.example.Ticketing.Models.CSE;
import com.example.Ticketing.Models.General_Manager;
import com.example.Ticketing.RequestModel.CSERequestModel;
import com.example.Ticketing.Services.CSEService;
import com.example.Ticketing.Services.General_ManagerService;
import com.example.Ticketing.Services.Operations_ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class TicketingApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(TicketingApplication.class,args);

    }

    @Autowired
    private General_ManagerService general_managerService;

    @Autowired
    private Operations_ManagerService operations_managerService;

    @Autowired
    private CSEService cseService;


    @Override
    public void run(String... args) throws Exception {

        CSERequestModel cseRequestModel= new CSERequestModel();
        cseRequestModel.setName("Nada");
        cseRequestModel.setEmail("nadaayari@gmail.com");
        cseRequestModel.setPassword("nadaGetWireless");
        cseRequestModel.setFamilyname("Ayari");
        cseRequestModel.setPhone_number("29051999");
        cseService.register(cseRequestModel);
        CSERequestModel cseRequestModel1= new CSERequestModel();
        cseRequestModel1.setName("Nesine");
        cseRequestModel1.setEmail("nesrinerouis@gmail.com");
        cseRequestModel1.setPassword("nesrineGetWireless");
        cseRequestModel1.setFamilyname("Rouis");
        cseRequestModel1.setPhone_number("29051999");
        cseService.register(cseRequestModel1);


    }
}

