package ru.bellintegrator.practice.model;

import javax.persistence.*;

//@Entity
//@Table(name = "Countries")
public class Countries {

    @Id
    @GeneratedValue
    @Column(name = "code")
    @OneToOne(optional = false, mappedBy = "citizenship_code")
    private Long code;

    @Basic(optional = false)
    @Column(name = "citizenship_name")
    private int citizenshipName;

    @Version
    private int version;

    public Countries() {
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public int getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(int citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
