package com.neu.buybook.model;

public class SubType {
    private Integer id;

    private String code;

    private String name;

    private Integer mainTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMainTypeId() {
        return mainTypeId;
    }

    public void setMainTypeId(Integer mainTypeId) {
        this.mainTypeId = mainTypeId;
    }
}