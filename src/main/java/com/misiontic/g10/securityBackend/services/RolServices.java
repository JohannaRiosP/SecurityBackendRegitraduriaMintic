package com.misiontic.g10.securityBackend.services;

import com.misiontic.g10.securityBackend.models.Rol;
import com.misiontic.g10.securityBackend.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RolServices {
    @Autowired
    private RolRepository rolRepository;
    /**
     * All users
     */
    public  List<Rol> index(){
        return (List<Rol>)this.rolRepository.findAll();

    }


    public Optional<Rol> show(int idRol){
        return this.rolRepository.findById(idRol);
    }
    public Rol create(Rol newRol){
        if(newRol.getIdRol() != null){
            if(newRol.getRolName() != null){
                return this.rolRepository.save(newRol);
            }
            else {
                return newRol;
            }
        }
        else {
            return newRol;
        }
    }
    public Rol update(int idRol, Rol updatedRol){
        if(idRol > 0){
            Optional<Rol> tempRol = this.show(idRol);
            if(tempRol.isPresent()){
                if(updatedRol.getRolName() != null)
                    tempRol.get().setRolName(updatedRol.getRolName());
                return this.rolRepository.save(tempRol.get());
            }
            else{
                return updatedRol;
            }
        }
        else{
            return updatedRol;
        }
    }

    public boolean delete(int idRol){
        Boolean success = this.show(idRol).map(rol -> {
            this.rolRepository.delete(rol);
            return true;
        }
        ).orElse(false);
        return success;
    }
}

