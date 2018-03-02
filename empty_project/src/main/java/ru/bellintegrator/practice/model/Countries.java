package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Countries")
public class Countries {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    @MapsId
    private Documentation code;

    @Basic(optional = false)
    @Column(name = "citizenship_name")
    private int citizenshipName;

    @Version
    private int version;

    public Countries() {
    }

    public Long getId() {
        return id;
    }

    public Documentation getCode() {
        return code;
    }

    public void setCode(Documentation code) {
        this.code = code;
    }

    public int getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(int citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Countries)) return false;
        Countries countries = (Countries) o;
        return getCitizenshipName() == countries.getCitizenshipName() &&
                version == countries.version &&
                Objects.equals(getId(), countries.getId()) &&
                Objects.equals(getCode(), countries.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getCitizenshipName(), version);
    }
}
