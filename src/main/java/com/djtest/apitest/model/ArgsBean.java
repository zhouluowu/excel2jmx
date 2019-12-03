package com.djtest.apitest.model;

public class ArgsBean {
    private String type;
    private String name;
    private String value;

    public ArgsBean(){

    }

    public ArgsBean(String name, String value, String type){
        this.name=name;
        this.value=value;
        this.type=type;
    }

    public ArgsBean(String name, String value){
        this.name=name;
        this.value=value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
