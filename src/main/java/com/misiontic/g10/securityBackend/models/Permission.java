package com.misiontic.g10.securityBackend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="permission")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermission;
    @Column(name = "url", nullable = false, unique = true)
    private String url;
    @Column(name = "method", nullable = false, unique = true)
    private String method;

    @ManyToMany(mappedBy = "permissions")
    private Set<Rol> roles;

    public Integer getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Integer idPermission) {
        this.idPermission = idPermission;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {

        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
