package ru.bellintegrator.eas.view;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserView {

    @ApiModelProperty(hidden = true)
    private String id;

    private String firstName;

    private String secondName;

    private String middleName;

    private String position;

    private int phone;

    private int docCode;

    private String docName;

    private int docNumber;

    private Date docDate;

    private int citizenshipCode;

    private String citizenshipName;

    private boolean isIdentified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public String toString() {
        return "UserView{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone=" + phone +
                ", docCode=" + docCode +
                ", docNumber=" + docNumber +
                ", docDate=" + docDate +
                ", citizenshipCode=" + citizenshipCode +
                ", citizenshipName=" + citizenshipName +
                ", isIdentified=" + isIdentified +
                '}';
    }
}
