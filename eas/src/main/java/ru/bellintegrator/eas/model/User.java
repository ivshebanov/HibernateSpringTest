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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Version
    private int version;

    @Basic(optional = false)
    @Column(name = "first_name")
    private String firstName;

    @Basic(optional = false)
    @Column(name = "second_name")
    private String secondName;

    @Basic(optional = false)
    @Column(name = "middle_name")
    private String middleName;

    @Basic(optional = false)
    @Column(name = "position")
    private String position;

    @Basic(optional = false)
    @Column(name = "phone")
    private int phone;

    @Basic(optional = false)
    @Column(name = "doc_number")
    private int docNumber;

    @Basic(optional = false)
    @Column(name = "doc_date")
    private Date docDate;

    @Basic(optional = false)
    @Column(name = "is_identified")
    private boolean isIdentified;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_type_id")
    private Doc doc;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "office_id")
    private Office officeId;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(int docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
        return version == user.version &&
                getPhone() == user.getPhone() &&
                getDocNumber() == user.getDocNumber() &&
                isIdentified() == user.isIdentified() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getSecondName(), user.getSecondName()) &&
                Objects.equals(getMiddleName(), user.getMiddleName()) &&
                Objects.equals(getPosition(), user.getPosition()) &&
                Objects.equals(getDocDate(), user.getDocDate()) &&
                Objects.equals(getDoc(), user.getDoc()) &&
                Objects.equals(getCountry(), user.getCountry()) &&
                Objects.equals(getOfficeId(), user.getOfficeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), version, getFirstName(), getSecondName(), getMiddleName(), getPosition(), getPhone(), getDocNumber(), getDocDate(), isIdentified(), getDoc(), getCountry(), getOfficeId());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone=" + phone +
                ", docNumber=" + docNumber +
                ", docDate=" + docDate +
                ", isIdentified=" + isIdentified +
                ", doc=" + doc +
                ", country=" + country +
                ", officeId=" + officeId +
                '}';
    }
}
