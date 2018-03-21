package ru.bellintegrator.eas.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Version
    private int version;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "full_name")
    private String fullName;

    @Basic(optional = false)
    @Column(name = "login")
    private String login;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

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

    @Basic(optional = false)
    @Column(name = "hash_active")
    private String hashActive;

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

    public String getHashActive() {
        return hashActive;
    }

    public void setHashActive(String hashActive) {
        this.hashActive = hashActive;
    }

    public List<Office> getOffices() {
        return offices;
    }

    public void setOffices(List<Office> offices) {
        this.offices = offices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization)) return false;
        Organization that = (Organization) o;
        return version == that.version &&
                getInn() == that.getInn() &&
                getKpp() == that.getKpp() &&
                getPhone() == that.getPhone() &&
                isActive() == that.isActive() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getFullName(), that.getFullName()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getAddress(), that.getAddress()) &&
                Objects.equals(getHashActive(), that.getHashActive()) &&
                Objects.equals(getOffices(), that.getOffices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), version, getName(), getFullName(), getLogin(), getPassword(), getInn(), getKpp(), getAddress(), getPhone(), isActive(), getHashActive(), getOffices());
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", inn=" + inn +
                ", kpp=" + kpp +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", isActive=" + isActive +
                ", hashActive='" + hashActive + '\'' +
                ", offices=" + offices +
                '}';
    }
}
