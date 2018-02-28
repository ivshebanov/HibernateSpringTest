package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "name")
    private Register name;

    @Basic(optional = false)
    @Column(name = "full_name")
    private String fullName;

    @Basic(optional = false)
    @Column(name = "inn")
    private int inn;

    @Basic(optional = false)
    @Column(name = "kpp")
    private int kpp;

    @Basic(optional = false)
    @Column(name = "address")
    private String address;

    @Basic(optional = false)
    @Column(name = "phone")
    private int phone;

    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;

    @Version
    private int version;

    public Organization() {
    }

    public Long getId() {
        return id;
    }

    public Register getName() {
        return name;
    }

    public void setName(Register name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public int getKpp() {
        return kpp;
    }

    public void setKpp(int kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
