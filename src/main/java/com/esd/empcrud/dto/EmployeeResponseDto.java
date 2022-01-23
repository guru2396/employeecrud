package com.esd.empcrud.dto;

public class EmployeeResponseDto {

    private Integer emp_id;

    private String emp_name;

    private String emp_contact;

    private String emp_dept;

    private String emp_gender;

    private String image;

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setEmp_contact(String emp_contact) {
        this.emp_contact = emp_contact;
    }

    public void setEmp_dept(String emp_dept) {
        this.emp_dept = emp_dept;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getEmp_contact() {
        return emp_contact;
    }

    public String getEmp_dept() {
        return emp_dept;
    }

    public String getImage() {
        return image;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }
}
