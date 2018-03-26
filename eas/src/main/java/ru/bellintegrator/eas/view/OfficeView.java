package ru.bellintegrator.eas.view;

import io.swagger.annotations.ApiModelProperty;

public class OfficeView {

    @ApiModelProperty(hidden = true)
    private String id;

    private String name;

    private String address;

    private int phone;

    private boolean isActive;

    @Override
    public String toString() {
        return "OfficeView{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", isActive=" + isActive +
                '}';
    }
}
