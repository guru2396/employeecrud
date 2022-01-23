package com.esd.empcrud.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeUpdateDto {

    private String currEmpId;

    private String newEmpId;

    private String emp_name;

    private String emp_dept;

    private String emp_contact;

    private String emp_gender;

    private MultipartFile image;

    public String getEmp_name() {
        return emp_name;
    }

    public String getEmp_dept() {
        return emp_dept;
    }

    public String getEmp_contact() {
        return emp_contact;
    }

    public MultipartFile getImage() {
        return image;
    }

    public String getCurrEmpId() {
        return currEmpId;
    }

    public String getNewEmpId() {
        return newEmpId;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public void setCurrEmpId(String currEmpId) {
        this.currEmpId = currEmpId;
    }

    public void setNewEmpId(String newEmpId) {
        this.newEmpId = newEmpId;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setEmp_dept(String emp_dept) {
        this.emp_dept = emp_dept;
    }

    public void setEmp_contact(String emp_contact) {
        this.emp_contact = emp_contact;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
