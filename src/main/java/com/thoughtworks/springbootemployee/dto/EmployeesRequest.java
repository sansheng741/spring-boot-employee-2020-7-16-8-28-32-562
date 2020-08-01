package com.thoughtworks.springbootemployee.dto;

public class EmployeesRequest {
    private String name;
    private String gender;
    private int age;
    private int companyId;

    public EmployeesRequest() {
    }

    public EmployeesRequest(String name, String gender, int age, int companyId) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
