package ru.bellintegrator.eas.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Table(name = "Register")
public class Register {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "login")
    private String login;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @Column(name = "hashActive")
    private String hashActive;

    @Version
    private int version;

    public Register() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHashActive() {
        return hashActive;
    }

    public void setHashActive(String hashActive) {
        this.hashActive = hashActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Register)) return false;
        Register register = (Register) o;
        return version == register.version &&
                Objects.equals(getId(), register.getId()) &&
                Objects.equals(getName(), register.getName()) &&
                Objects.equals(getLogin(), register.getLogin()) &&
                Objects.equals(getPassword(), register.getPassword());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getLogin(), getPassword(), version);
    }
}
