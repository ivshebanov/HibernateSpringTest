package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.ArrayList;

//@Entity
//@Table(name = "Office")
public class Office {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "address")
    private String address;

    @Basic(optional = false)
    @Column(name = "phone")
    private int phone;

    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id")
    private int orgId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "officeId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ArrayList<User> users;

    @Version
    private int version;

    public Office() {
    }

    public Office(String name, String address, int phone, boolean isActive) {
        this.name = name;
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

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
