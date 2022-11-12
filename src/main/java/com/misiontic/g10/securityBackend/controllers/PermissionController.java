package com.misiontic.g10.securityBackend.controllers;

import com.misiontic.g10.securityBackend.models.Permission;
import com.misiontic.g10.securityBackend.services.PermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Permission insertPermission(@RequestBody Permission permission){
        return this.permissionServices.create(permission);
    }

    @PutMapping("/updated/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Permission updatePermission(@PathVariable("id") int idPermission, @RequestBody Permission permission){
        return this.permissionServices.update(idPermission, permission);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deletePermission(@PathVariable("id") int idPermission){
        return this.permissionServices.delete(idPermission);
    }
}