package com.misiontic.g10.securityBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name="idRol")
    @JsonIgnoreProperties ("users")
    private Rol rol;

    /**
     *
     * @return
     */
    public Integer getIdUser() {
        return idUser;
    }

    /**
     *
     * @return
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */

    public Rol getRol() {
        return rol;
    }

    /**
     *
     * @param rol
     */

    public void setRol(Rol rol) {
        this.rol = rol;
    }


}

