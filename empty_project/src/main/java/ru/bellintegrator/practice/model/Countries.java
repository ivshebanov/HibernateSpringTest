package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Countries")
public class Countries {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(name = "code")
    private int code;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(int citizenshipName) {
        this.citizenshipName = citizenshipName;
    }
}
