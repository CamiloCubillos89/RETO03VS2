package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog6.CrudRepository.AdminCrudRepository;
import com.reto03.grupog6.Entities.Admin;


@Repository
public class AdminRepository {
    @Autowired
    private AdminCrudRepository adminCrudRepository;

    //retorna todas las entidades en BD
    public List<Admin> getAll() {
        return (List<Admin>) adminCrudRepository.findAll();
    }

    //guarda la entidad en BD
    public Admin addAdmin(Admin admin) {
        if (admin.getIdAdmin() == null || admin.getIdAdmin() == 0)
            return adminCrudRepository.save(admin);
        else
            return admin;
    }

    //Determina si el Admin existe en BD
    private Admin existAdmin(Integer idAdmin) {
        Optional<Admin> objAdmin = adminCrudRepository.findById(idAdmin);
        Admin objAdminRespuesta;

        if (objAdmin.isEmpty())
            objAdminRespuesta = null;
        else
            objAdminRespuesta = objAdmin.get();
        
        return objAdminRespuesta;
    }

    //Actualiza datos
    public Admin updAdmin(Admin admin) {
        Admin objAdmin;

        objAdmin = existAdmin(admin.getIdAdmin());
        if (objAdmin == null)
            return admin;
        else {
            if (admin.getName() != null)
                objAdmin.setName(admin.getName());

            if (admin.getEmail() != null)
                objAdmin.setEmail(admin.getEmail());

            if (admin.getPassword() != null)
                objAdmin.setPassword(admin.getPassword());

            return adminCrudRepository.save(objAdmin);

        }
    }

    //Elimina datos
    public void delAdmin(Integer idAdmin) {
        Admin objAdmin;

        objAdmin = existAdmin(idAdmin);
        if (objAdmin != null)
            adminCrudRepository.delete(objAdmin);
    }

    //Trae datos
    public Admin getAdmin(Integer idAdmin) {
        Admin objAdmin;

        objAdmin = existAdmin(idAdmin);
        if (objAdmin != null)
            return objAdmin;
        else
            return null;
        
    }

    public Admin getAdminByEmailAndPassword(String email, String password) {
        return adminCrudRepository.findByEmailAndPassword(email, password);
    }
    
}
