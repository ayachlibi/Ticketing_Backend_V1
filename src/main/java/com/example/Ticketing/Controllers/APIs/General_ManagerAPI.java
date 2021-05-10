package com.example.Ticketing.Controllers.APIs;

import com.example.Ticketing.Models.General_Manager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface General_ManagerAPI {

    @PostMapping(value = "/General_Manager/AddGeneral_Manager", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    General_Manager saveGeneral_Manager(@RequestBody General_Manager gm);

    @DeleteMapping(value = "/General_Manager/Delete/{idGeneral_Manager}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteGeneral_Manager(@PathVariable("idGeneral_Manager") Long id);

    @PutMapping(value = "/General_Manager/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    General_Manager updateGeneral_Manager(@RequestBody General_Manager gm);

    @GetMapping(value = "/General_Manager/{idGeneral_Manager}")
    Optional<General_Manager> findGeneral_ManagerById(@PathVariable("idGeneral_Manager") Long id);

    @GetMapping(value = "/General_Manager/all")
    List<General_Manager> findAllGeneral_Managers();
}
