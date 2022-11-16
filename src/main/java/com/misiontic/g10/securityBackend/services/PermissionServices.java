package com.misiontic.g10.securityBackend.services;
import com.misiontic.g10.securityBackend.models.Permission;
import com.misiontic.g10.securityBackend.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    public Optional<Permission> show(int idPermission){
        return this.permissionRepository.findById(idPermission);
    }
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
    public Permission update(int idPermission, Permission updatedPermission){
        if(idPermission > 0){
            Optional<Permission> tempPermission = this.show(idPermission);
            if(tempPermission.isPresent()){
                if(updatedPermission.getUrl() != null)
                    tempPermission.get().setUrl(updatedPermission.getUrl());
                return this.permissionRepository.save(tempPermission.get());
            }
            else{
                return updatedPermission;
            }
        }
        else{
            return updatedPermission;
        }
    }

    public boolean delete(int idPermission){
        Boolean success = this.show(idPermission).map(permission -> {
            this.permissionRepository.delete(permission);
            return true;
        }
        ).orElse(false);
        return success;
    }
}

