package com.misiontic.g10.securityBackend.controllers;
import com.misiontic.g10.securityBackend.models.Rol;
import com.misiontic.g10.securityBackend.models.Permission;
import com.misiontic.g10.securityBackend.services.RolServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
@CrossOrigin
@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolServices rolServices;

    @GetMapping("/all")
    public List<Rol> getAllRoles(){

        return this.rolServices.index();
    }

    @GetMapping("/{id}")
    public Optional<Rol> getRolById(@PathVariable("id") int idRol){

        return  this.rolServices.show(idRol);
    }

    @GetMapping("/validate/{idRol}")
    public ResponseEntity<Boolean> getValidation(@PathVariable("idRol") int idRol, @RequestBody Permission permission){
        return this.rolServices.validateGrant(idRol, permission);
    }

    @PostMapping("/insert")
    public Rol insertRol(@RequestBody Rol rol){

        return this.rolServices.create(rol);
    }

    @PutMapping("/updated/{id}")
    public Rol updateRol(@PathVariable("id") int idRol, @RequestBody Rol rol){
        return this.rolServices.update(idRol, rol);
    }

    @PutMapping("/update/{idRol}/add_permission/{idPermission}")
    public ResponseEntity<Rol> updateRolAddPermission(@PathVariable ("idRol") int idRol, @PathVariable("idPermission") int idPermission){
        return this.rolServices.updateAddPermission(idRol, idPermission);
    }

    @PutMapping("/update/{idRol}/remove_permission/{idPermission}")
    public ResponseEntity<Rol> updateRolRemovePermission(@PathVariable("idRol") int idRol, @PathVariable("idPermission") int idPermission){
        return this.rolServices.removePermission(idRol, idPermission);
    }
    @DeleteMapping("/delete/{id}")
    public boolean deleteRol(@PathVariable("id") int idRol){

        return this.rolServices.delete(idRol);
    }
}