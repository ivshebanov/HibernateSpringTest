package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Register")
public class Register {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @OneToOne(optional = false, mappedBy = "name")
    @JoinColumn(name = "name")
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
}
