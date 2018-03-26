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

    private int docNumber;

    private Date docDate;

    private int citizenshipCode;

    private int citizenshipName;

    private boolean isIdentified;

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
