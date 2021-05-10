package com.example.Ticketing.Services;

import com.example.Ticketing.Models.*;

import java.util.List;
import java.util.Optional;

public interface Operations_ManagerService {

    Operations_Manager save(Operations_Manager om);

    Operations_Manager update(Operations_Manager om);

    void delete(Long id);

    Optional<Operations_Manager> findById(Long id);

    List<Operations_Manager> findAll();
}
