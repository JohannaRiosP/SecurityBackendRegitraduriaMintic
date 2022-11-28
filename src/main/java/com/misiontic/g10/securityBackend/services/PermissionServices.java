package com.misiontic.g10.securityBackend.services;
import com.misiontic.g10.securityBackend.models.Permission;
import com.misiontic.g10.securityBackend.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServices {
    @Autowired
    private PermissionRepository permissionRepository;
    /**
     * All users
     */
    public List<Permission> index(){
        return (List<Permission>)this.permissionRepository.findAll();

    }

    /**
     *
     * @param idPermission
     * @return
     */
    public Optional<Permission> show(int idPermission){
        return this.permissionRepository.findById(idPermission);
    }

    /**
     *
     * @param newPermission
     * @return
     */
    public Permission create(Permission newPermission){
        if(newPermission.getIdPermission() == null){
            if(newPermission.getUrl() != null && newPermission.getMethod() != null){
                return this.permissionRepository.save(newPermission);
            }
            else {
                return newPermission;
            }
        }
        else {
            return newPermission;
        }
    }

    /**
     *
     * @param idPermission
     * @param updatedPermission
     * @return
     */
    public Permission update(int idPermission, Permission updatedPermission){
        if(idPermission > 0){
            Optional<Permission> tempPermission = this.show(idPermission);
            if(tempPermission.isPresent()){
                if(updatedPermission.getUrl() != null)
                    tempPermission.get().setUrl(updatedPermission.getUrl());
                if(updatedPermission.getMethod() != null)
                    tempPermission.get().setMethod(updatedPermission.getMethod());
                return this.permissionRepository.save(tempPermission.get());
            }
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Requested permission does not exist in the database.");
        }
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Permission ID cannot be negative.");

    }

    /**
     *
     * @param idPermission
     * @return
     */
    public boolean delete(int idPermission){
        Boolean success = this.show(idPermission).map(permission -> {
            this.permissionRepository.delete(permission);
            return true;
        }
        ).orElse(false);
        return success;
    }
}

