package com.example.Ticketing.Service;

import com.example.Ticketing.Models.CSE;

import java.util.List;
import java.util.Optional;

public interface CSEService {

    public CSE addCSE(CSE cse);

    public void deleteCSE(Long id);

    public CSE updateCSE(CSE cse);

    public List<CSE> findAllCSEs();

    public Optional<CSE> findCSEById(Long id);
}
