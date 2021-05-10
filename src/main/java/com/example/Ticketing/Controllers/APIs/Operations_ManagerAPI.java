package com.example.Ticketing.Controllers.APIs;

import com.example.Ticketing.Models.Operations_Manager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface Operations_ManagerAPI {

    @PostMapping(value = "/Operations_Manager/AddOperations_Manager", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Operations_Manager saveOperations_Manager(@RequestBody Operations_Manager op);

    @DeleteMapping(value = "/Operations_Manager/Delete/{idOperations_Manager}", produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteOperations_Manager(@PathVariable("idOperations_Manager") Long id);

    @PutMapping(value = "/Operations_Manager/Update", produces = MediaType.APPLICATION_JSON_VALUE)
    Operations_Manager updateOperations_Manager(@RequestBody Operations_Manager op);

    @GetMapping(value = "/Operations_Manager/{idOperations_Manager}")
    Optional<Operations_Manager> findOperations_ManagerById(@PathVariable("idOperations_Manager") Long id);

    @GetMapping(value = "/Operations_Manager/all")
    List<Operations_Manager> findAllOperations_Managers();
}
