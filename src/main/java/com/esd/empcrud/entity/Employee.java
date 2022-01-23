package com.esd.empcrud.entity;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @Column(name="emp_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer emp_pk;

    @Column(name="emp_id")
    private Integer emp_id;

    @Column(name="emp_name")
    private String emp_name;

    @Column(name="emp_imgurl")
    private String emp_imgurl;

    @Column(name="emp_dept")
    private Integer emp_dept;

    @Column(name="emp_contact")
    private String emp_contact;

    @Column(name="emp_isdeleted")
    private String emp_isdeleted;

    @Column(name="emp_gender")
    private String emp_gender;

    public Integer getEmp_pk() {
        return emp_pk;
    }

   public Integer getEmp_id() {
       return emp_id;
   }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public String getEmp_imgurl() {
        return emp_imgurl;
    }

    public Integer getEmp_dept() {
        return emp_dept;
    }

    public String getEmp_isdeleted() {
        return emp_isdeleted;
    }

    public void setEmp_pk(Integer emp_pk) {
        this.emp_pk = emp_pk;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
   }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setEmp_imgurl(String emp_imgurl) {
        this.emp_imgurl = emp_imgurl;
    }

    public void setEmp_dept(Integer emp_dept) {
        this.emp_dept = emp_dept;
    }

    public void setEmp_isdeleted(String emp_isdeleted) {
        this.emp_isdeleted = emp_isdeleted;
    }

    public String getEmp_contact() {
        return emp_contact;
    }

    public void setEmp_contact(String emp_contact) {
        this.emp_contact = emp_contact;
    }
}
