package com.example.Ticketing.Controllers.API;

import com.example.Ticketing.Models.Admin;
import com.example.Ticketing.Models.SuperAdmin;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface SuperAdminAPI {

    @PostMapping(value = "/SuperAdmin/AddaNewSuperAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SuperAdmin saveSuperAdmin(@RequestBody SuperAdmin superAdmin);

    @DeleteMapping(value = "/SuperAdmin/Delete/{idSuperAdmin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSuperAdmin(@PathVariable("idSuperAdmin") Long id);

    @PutMapping(value = "/SuperAdmin/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    public SuperAdmin updateSuperAdmin(@RequestBody SuperAdmin superAdmin);

    @GetMapping(value = "/SuperAdmin/{idSuperAdmin}")
    public Optional<SuperAdmin> findSuperAdminById(@PathVariable("idSuperAdmin") Long id);

    @GetMapping(value = "/SuperAdmin/all")
    public List<SuperAdmin> findAllSuperAdmins();

}
