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
        Admin a1= new Admin();
        a1.setName("Seddik");
        a1.setFamilyname("Moussa");
        a1.setUsername("MoussaSeddik");
        a1.setPassword("M");
        a1.setEmail("seddikmoussa@gmail.com");
        a1.setPhone_number(1321546);
        Client c1= new Client();
        c1.setName("Hammadi");
        c1.setFamilyname("Abid");
        c1.setUsername("HammadiAbid");
        c1.setPassword("HA");
        c1.setEmail("hammadiabid@gmail.com");
        c1.setPhone_number(131546843);
        c1.setEnterprise("Hammadi Abid Boutique");
        List<CSE> cses = new ArrayList<>();
        CSE cse1= new CSE();
        cse1.setName("Aya");
        cse1.setFamilyname("Chlibi");
        cse1.setUsername("ChlibiAya");
        cse1.setPassword("A");
        cse1.setEmail("ayachlibi@gmail.com");
        cse1.setPhone_number(55612351);
        CSE cse2= new CSE();
        cse2.setName("Douaa");
        cse2.setFamilyname("Mami");
        cse2.setUsername("MemiDouaa");
        cse2.setPassword("MD");
        cse2.setEmail("Douaamami@gmail.com");
        cse2.setPhone_number(26153251);
        Ticket t1= new Ticket();
        t1.setCse(cse1);
        t1.setClient(c1);
        t1.setCategory("kfkf");
        t1.setDescription("31kvhvkerhvberbuv envieurbvuerbuvervn nevnekrnvk");
        t1.setTitle("Server problem");
        t1.setType("fhjsdjef");
        cses.add(cse1);
        cses.add(cse2);
        c1.setCses(cses);
        clientService.addClient(c1);
        adminService.addAdmin(a1);
        cseService.addCSE(cse1);
        cseService.addCSE(cse2);
        ticketService.addTicket(t1);
    }
    }

