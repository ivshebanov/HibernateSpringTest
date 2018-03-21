package ru.bellintegrator.eas.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @Column(name = "doc_code")
    private int docCode;

    @Basic(optional = false)
    @Column(name = "doc_name")
    private String docName;

    @Basic(optional = false)
    @Column(name = "doc_number")
    private int docNumber;

    @Basic(optional = false)
    @Column(name = "doc_date")
    private Date docDate;

    @Basic(optional = false)
    @Column(name = "citizenship_code")
    private int citizenshipCode;

    @Basic(optional = false)
    @Column(name = "citizenship_name")
    private String citizenshipName;

    @Basic(optional = false)
    @Column(name = "is_identified")
    private boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public int getDocCode() {
        return docCode;
    }

    public void setDocCode(int docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
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

    public int getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(int citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
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
                getDocCode() == user.getDocCode() &&
                getDocNumber() == user.getDocNumber() &&
                getCitizenshipCode() == user.getCitizenshipCode() &&
                isIdentified() == user.isIdentified() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getSecondName(), user.getSecondName()) &&
                Objects.equals(getMiddleName(), user.getMiddleName()) &&
                Objects.equals(getPosition(), user.getPosition()) &&
                Objects.equals(getDocName(), user.getDocName()) &&
                Objects.equals(getDocDate(), user.getDocDate()) &&
                Objects.equals(getCitizenshipName(), user.getCitizenshipName()) &&
                Objects.equals(getOfficeId(), user.getOfficeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), version, getFirstName(), getSecondName(), getMiddleName(), getPosition(), getPhone(), getDocCode(), getDocName(), getDocNumber(), getDocDate(), getCitizenshipCode(), getCitizenshipName(), isIdentified(), getOfficeId());
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
                ", docCode=" + docCode +
                ", docName='" + docName + '\'' +
                ", docNumber=" + docNumber +
                ", docDate=" + docDate +
                ", citizenshipCode=" + citizenshipCode +
                ", citizenshipName='" + citizenshipName + '\'' +
                ", isIdentified=" + isIdentified +
                ", officeId=" + officeId +
                '}';
    }
}
