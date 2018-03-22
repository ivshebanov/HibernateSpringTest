package ru.bellintegrator.eas.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

@Entity
@Table(name = "Country")
public class Country {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(name = "code")
    private int code;

    @Version
    private int version;

    @Basic(optional = false)
    @Column(name = "citizenship_name")
    private String citizenshipName;

    @OneToOne(optional = false, mappedBy = "country")
    private User user;

    public Country() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return getCode() == country.getCode() &&
                version == country.version &&
                Objects.equals(getId(), country.getId()) &&
                Objects.equals(getCitizenshipName(), country.getCitizenshipName()) &&
                Objects.equals(getUser(), country.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), version, getCitizenshipName(), getUser());
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", code=" + code +
                ", version=" + version +
                ", citizenshipName='" + citizenshipName + '\'' +
                ", user=" + user +
                '}';
    }
}
