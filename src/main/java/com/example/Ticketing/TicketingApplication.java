package com.example.Ticketing;

import com.example.Ticketing.Config.SequenceGeneratorService;
import com.example.Ticketing.Models.*;
import com.example.Ticketing.Repository.*;
import com.example.Ticketing.Service.AdminService;
import com.example.Ticketing.Service.CSEService;
import com.example.Ticketing.Service.ClientService;
import com.example.Ticketing.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.example.Ticketing.Models.User.*;

@SpringBootApplication

public class TicketingApplication implements CommandLineRunner {



    public static void main(String[] args) {

        SpringApplication.run(TicketingApplication.class,args);

    }

    @Autowired
    private CSERepository cseRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Autowired
    private CSEService cseService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TicketService ticketService;


    @Override
    public void run(String... args) throws Exception {

        Client c1=new Client();
        c1.setName("Hammadi");
        c1.setFamilyname("Abid");
        c1.setUsername("HammadiAbi");
        c1.setPassword("HA");
        c1.setEnterprise("Hammadi Abid Boutique");
        c1.setEmail("hammadiabi@gmail.com");
        c1.setPhone_number(23032012);
        clientService.addClient(c1);

        Admin a1= new Admin();
        a1.setName("Seddik");
        a1.setFamilyname("Moussa");
        a1.setUsername("MoussaSeddi");
        a1.setPassword("M");
        a1.setEmail("seddikmouss@gmail.com");
        a1.setPhone_number(1321546);
        adminService.addAdmin(a1);
/*
        CSE cse1= new CSE();
        cse1.setUsername("AyaChlib");
        cse1.setPassword("AC");
        cse1.setClient(c1);
        cse1.setName("Aya");
        cse1.setFamilyname("Chlibi");
        cse1.setEmail("ayachlib@gmail.com");
        cse1.setPhone_number(55612351);
        cse1.setAdmin(a1);
        cseService.addCSE(cse1);

        CSE cse2= new CSE();
        cse2.setUsername("DouaaMam");
        cse2.setPassword("DM");
        cse2.setClient(c1);
        cse2.setName("Douaa");
        cse2.setFamilyname("Mami");
        cse2.setEmail("douaamam@gmail.com");
        cse2.setPhone_number(22315203);
        cse2.setAdmin(a1);
        cseService.addCSE(cse2);
*/
    }


    }

