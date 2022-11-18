package com.misiontic.g10.securityBackend.services;
import com.misiontic.g10.securityBackend.models.Rol;
import com.misiontic.g10.securityBackend.models.Permission;
import com.misiontic.g10.securityBackend.repositories.PermissionRepository;
import com.misiontic.g10.securityBackend.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolServices {
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    /**
     * All users
     */
    public  List<Rol> index(){
        return (List<Rol>)this.rolRepository.findAll();

    }

    public Optional<Rol> show(Integer idRol){

        return this.rolRepository.findById(idRol);
    }
    public Rol create(Rol newRol){
        if(newRol.getIdRol() == null){
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
    public Rol update(Integer idRol, Rol updatedRol){
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

    public boolean delete(Integer idRol){
        Boolean success = this.show(idRol).map(rol -> {
            this.rolRepository.delete(rol);
            return true;
        }
        ).orElse(false);
        return success;
    }

    public ResponseEntity<Rol> updatedAddPermission(int idRol, int idPermission){
        Optional<Rol> rol = this.rolRepository.findById(idRol);
        if(rol.isPresent()){
            Optional<Permission> permission = this.permissionRepository.findById(idPermission);
            if (permission.isPresent()){
                Set<Permission> tempPermissions = rol.get().getPermissions();
                if(tempPermissions.contains(permission))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Rol has the permission");
                else{
                    tempPermissions.add(permission.get());
                    rol.get().setPermissions(tempPermissions);
                    return new ResponseEntity<>(this.rolRepository.save(rol.get()), HttpStatus.CREATED);
                    }
            }
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "The provided permission.id does not exist in Database");
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "The provided rol.id does not exist in Database");
        }
    public ResponseEntity<Boolean> validateGrant(int idRol, Permission permission){
        boolean isGrant = false;
        Optional<Rol> rol = this.rolRepository.findById(idRol);
        if(rol.isPresent()){
            for (Permission rolPermission: rol.get().getPermissions()){
                if(rolPermission.getUrl().equals(permission.getUrl()) &&
                        rolPermission.getMethod().equals(permission.getMethod())){
                    isGrant = true;
                    break;
                }
            }
            if (isGrant)
                return  new ResponseEntity<>(true, HttpStatus.OK);
            else
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "The provided rol.id does not exist id database");
    }

}

