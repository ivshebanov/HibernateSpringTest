package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.ArrayList;

//@Entity
//@Table(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name")
    private String name;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orgId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ArrayList<Office> offices;

    @Version
    private int version;

    public Organization() {
    }

    public Organization(String fullName, int inn, int kpp, String address, int phone, boolean isActive) {
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public ArrayList<Office> getOffices() {
        return offices;
    }

    public void setOffices(ArrayList<Office> offices) {
        this.offices = offices;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
