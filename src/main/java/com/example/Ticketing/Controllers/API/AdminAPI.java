package com.example.Ticketing.Controllers.API;

import com.example.Ticketing.Models.Admin;
import com.example.Ticketing.Models.Client;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


public interface AdminAPI {

    @PostMapping(value = "/Admin/AddNewAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Admin saveAdmin(@RequestBody Admin admin);

    @DeleteMapping(value = "/Admin/Delete/{idAdmin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteAdmin(@PathVariable("idAdmin") Long id);

    @PutMapping(value = "/Admin/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    public Admin updateAdmin(@RequestBody Admin admin);

    @GetMapping(value = "/Admin/{idAdmin}")
    public Optional<Admin> findAdminById(@PathVariable("idAdmin") Long id);

    @GetMapping(value = "/Admin/all")
    public List<Admin> findAllAdmins();


}
