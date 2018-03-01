package ru.bellintegrator.practice.model;

import javax.persistence.*;

@Entity
@Table(name = "Countries")
public class Countries {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
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
}
