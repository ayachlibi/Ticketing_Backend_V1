package com.example.Ticketing.Service;

import com.example.Ticketing.Models.Admin;
import com.example.Ticketing.Models.CSE;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public Admin addAdmin(Admin admin);

    public void deleteAdmin(Long id);

    public Admin updateAdmin(Admin admin);

    public List<Admin> findAllAdmins();

    public Optional<Admin> findAdminById(Long id);
}
