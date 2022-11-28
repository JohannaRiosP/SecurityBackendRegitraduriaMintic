package com.misiontic.g10.securityBackend.controllers;
import com.misiontic.g10.securityBackend.models.Permission;
import com.misiontic.g10.securityBackend.services.PermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionServices permissionServices;

    @GetMapping("/all")
    public List<Permission> getAllPermission(){
        return this.permissionServices.index();
    }

    @GetMapping("/{id}")
    public Optional<Permission> getPermissionById(@PathVariable("id") int idPermission){
        return  this.permissionServices.show(idPermission);
    }

    @PostMapping("/insert")
    public Permission insertPermission(@RequestBody Permission permission){
        return this.permissionServices.create(permission);
    }

    @PutMapping("/updated/{id}")
    public Permission updatePermission(@PathVariable("id") int idPermission, @RequestBody Permission permission){
        return this.permissionServices.update(idPermission, permission);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deletePermission(@PathVariable("id") int idPermission){
        return this.permissionServices.delete(idPermission);
    }
}