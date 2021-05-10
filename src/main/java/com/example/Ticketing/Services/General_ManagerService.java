package com.example.Ticketing.Services;

import com.example.Ticketing.Models.General_Manager;

import java.util.List;
import java.util.Optional;

public interface General_ManagerService {

    General_Manager save(General_Manager gm);

    General_Manager update(General_Manager gm);

    void delete(Long id);

    Optional<General_Manager> findById(Long id);

    List<General_Manager> findAll();
}
