package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
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

    @OneToMany(mappedBy = "orgId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Office> offices;

    public Organization() {
    }

    public void addOffice(Office office) {
        getOffices().add(office);
        office.setOrgId(this);
    }

    public void removeOffice(Office office) {
        getOffices().remove(office);
        office.setOrgId(null);
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return getInn() == that.getInn() &&
                getKpp() == that.getKpp() &&
                getPhone() == that.getPhone() &&
                isActive() == that.isActive() &&
                version == that.version &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getFullName(), that.getFullName()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getOffices(), that.getOffices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getFullName(), getInn(), getKpp(),
                getAddress(), getPhone(), isActive(), version, getOffices());
    }
}
