package ru.bellintegrator.practice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
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
    private Office officeId;

    @Version
    private int version;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Documentation> documentations;

    public User() {
    }

    public void addDocumentation(Documentation documentation) {
        getDocumentations().add(documentation);
        documentation.setUserId(this);
    }

    public void removeDocumentation(Documentation documentation) {
        getDocumentations().remove(documentation);
        documentation.setUserId(null);
    }

    public List<Documentation> getDocumentations() {
        return documentations;
    }

    public void setDocumentations(List<Documentation> documentations) {
        this.documentations = documentations;
    }

    public Long getId() {
        return id;
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

    public Office getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Office officeId) {
        this.officeId = officeId;
    }
}
