package com.esd.empcrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {

    @Id
    @Column(name="dept_id")
    private Integer dept_id;

    @Column(name="dept_name")
    private String dept_name;

    @Column(name="dept_capacity")
    private Integer dept_capacity;

    public Integer getDept_id() {
        return dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public Integer getDept_capacity() {
        return dept_capacity;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public void setDept_capacity(Integer dept_capacity) {
        this.dept_capacity = dept_capacity;
    }
}
