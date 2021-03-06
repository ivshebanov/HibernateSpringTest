package ru.bellintegrator.eas.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Office")
public class Office {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Version
    private int version;

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
    private Organization orgId;

    @OneToMany(mappedBy = "officeId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    public Office() {
    }

    public void addUser(User user) {
        getUsers().add(user);
        user.setOfficeId(this);
    }

    public void removeUser(User user) {
        getUsers().remove(user);
        user.setOfficeId(null);
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

    public Organization getOrgId() {
        return orgId;
    }

    public void setOrgId(Organization orgId) {
        this.orgId = orgId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Office)) return false;
        Office office = (Office) o;
        return version == office.version &&
                getPhone() == office.getPhone() &&
                isActive() == office.isActive() &&
                Objects.equals(getId(), office.getId()) &&
                Objects.equals(getName(), office.getName()) &&
                Objects.equals(getAddress(), office.getAddress()) &&
                Objects.equals(getOrgId(), office.getOrgId()) &&
                Objects.equals(getUsers(), office.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), version, getName(), getAddress(), getPhone(), isActive(), getOrgId(), getUsers());
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", version=" + version +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", isActive=" + isActive +
                ", orgId=" + orgId +
                ", users=" + users +
                '}';
    }
}
