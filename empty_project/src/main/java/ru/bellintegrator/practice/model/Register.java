package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Register")
public class Register {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne(mappedBy = "name", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Organization name;

    @Basic(optional = false)
    @Column(name = "login")
    private String login;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Version
    private int version;

    public Register() {
    }

    public Long getId() {
        return id;
    }

    public Organization getName() {
        return name;
    }

    public void setName(Organization name) {
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
