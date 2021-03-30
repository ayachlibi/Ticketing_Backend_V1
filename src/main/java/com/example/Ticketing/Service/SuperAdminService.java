package com.example.Ticketing.Service;

import com.example.Ticketing.Models.Admin;
import com.example.Ticketing.Models.SuperAdmin;

import java.util.List;
import java.util.Optional;

public interface SuperAdminService {

    public SuperAdmin addSuperAdmin(SuperAdmin superAdmin);

    public void deleteSuperAdmin(Long id);

    public SuperAdmin updateAdmin(SuperAdmin superAdmin);

    public List<SuperAdmin> findAllSuperAdmins();

    public Optional<SuperAdmin> findSuperAdminById(Long id);

}
