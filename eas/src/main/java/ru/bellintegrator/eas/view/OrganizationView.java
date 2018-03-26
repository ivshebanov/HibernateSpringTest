package ru.bellintegrator.eas.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationView {

    @ApiModelProperty(hidden = true)
    private String id;

    private String name;

    private String fullName;

    private String login;

    private String password;

    private int inn;

    private int kpp;

    private String address;

    private int phone;

    private boolean isActive;

    private String hashActive;

    @Override
    public String toString() {
        return "OrganizationView{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", inn=" + inn +
                ", kpp=" + kpp +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", isActive=" + isActive +
                ", hashActive='" + hashActive + '\'' +
                '}';
    }
}
