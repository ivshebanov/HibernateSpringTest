package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.ArrayList;

//@Entity
//@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "first_name")
    private int firstName;

    @Basic(optional = false)
    @Column(name = "second_name")
    private int secondName;

    @Basic(optional = false)
    @Column(name = "middle_name")
    private int middleName;

    @Basic(optional = false)
    @Column(name = "position")
    private int position;

    @Basic(optional = false)
    @Column(name = "phone")
    private int phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private int officeId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userId", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private ArrayList<Documentation> documentations;

    @Version
    private int version;

    public User() {
    }

    public User(int firstName, int secondName, int middleName, int position, int phone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.position = position;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFirstName() {
        return firstName;
    }

    public void setFirstName(int firstName) {
        this.firstName = firstName;
    }

    public int getSecondName() {
        return secondName;
    }

    public void setSecondName(int secondName) {
        this.secondName = secondName;
    }

    public int getMiddleName() {
        return middleName;
    }

    public void setMiddleName(int middleName) {
        this.middleName = middleName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public ArrayList<Documentation> getDocumentations() {
        return documentations;
    }

    public void setDocumentations(ArrayList<Documentation> documentations) {
        this.documentations = documentations;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
