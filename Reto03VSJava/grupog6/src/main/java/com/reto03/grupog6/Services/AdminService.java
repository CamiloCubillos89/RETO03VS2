package com.reto03.grupog6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Admin;
import com.reto03.grupog6.Repository.AdminRepository;

@Service//Encargada de usar el repository como parte de su lógica 
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin addAdmin(Admin admin) {
        Boolean bGrabar = true;

        if (admin.getName() == null)
            bGrabar = false;

        if (admin.getEmail() == null)
            bGrabar = false;

        if (admin.getPassword() == null)
            bGrabar = false;

        if (bGrabar)
            return adminRepository.addAdmin(admin);
        else
            return admin;

    }


    public List<Admin> getAll() {
        return (List<Admin>) adminRepository.getAll();
    }

    public Admin updAdmin(Admin admin) {
        return adminRepository.updAdmin(admin);
    }

    public Admin getAdmin(Integer idAdmin) {
        return adminRepository.getAdmin(idAdmin);
    }

    public void delAdmin(Integer idAdmin) {
        adminRepository.delAdmin(idAdmin);
    }

    public Admin getAdminByEmailAndPassword(String email, String password) {   
        return adminRepository.getAdminByEmailAndPassword(email, password);
    }
    
}