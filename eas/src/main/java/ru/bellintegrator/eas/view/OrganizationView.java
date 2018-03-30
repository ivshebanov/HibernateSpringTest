package ru.bellintegrator.eas.view;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationView {

    @ApiModelProperty(hidden = true)
    @Min(0)
    private String id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Size(max = 50)
    private String fullName;

    @NotNull
    @Size(min = 2, max = 100)
    private String login;

    @NotNull
    @Size(min = 5, max = 100)
    private String password;

    @NotNull
    @Min(0)
    @Max(20)
    private int inn;

    @NotNull
    @Min(0)
    @Max(20)
    private int kpp;

    @NotNull
    @Size(min = 2, max = 50)
    private String address;

    @NotNull
    @Min(0)
    @Max(20)
    private int phone;

    @NotNull
    private boolean isActive;

    private String hashActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public int getKpp() {
        return kpp;
    }

    public void setKpp(int kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getHashActive() {
        return hashActive;
    }

    public void setHashActive(String hashActive) {
        this.hashActive = hashActive;
    }

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
