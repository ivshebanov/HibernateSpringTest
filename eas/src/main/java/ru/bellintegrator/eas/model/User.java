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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getFirstName() == user.getFirstName() &&
                getSecondName() == user.getSecondName() &&
                getMiddleName() == user.getMiddleName() &&
                getPosition() == user.getPosition() &&
                getPhone() == user.getPhone() &&
                version == user.version &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getOfficeId(), user.getOfficeId()) &&
                Objects.equals(getDocumentations(), user.getDocumentations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getSecondName(), getMiddleName(),
                getPosition(), getPhone(), getOfficeId(), version, getDocumentations());
    }
}
