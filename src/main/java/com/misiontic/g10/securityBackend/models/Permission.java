package com.misiontic.g10.securityBackend.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="permission")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermission;
    private Integer namePermission;
    private String url;
    private String method;

    public Integer getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Integer idPermission) {
        this.idPermission = idPermission;
    }

    public Integer getNamePermission() {
        return namePermission;
    }

    public void setNamePermission(Integer namePermission) {
        this.namePermission = namePermission;
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
