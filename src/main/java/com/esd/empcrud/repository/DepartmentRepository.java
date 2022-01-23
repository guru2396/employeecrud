package com.esd.empcrud.repository;

import com.esd.empcrud.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query(value = "SELECT * FROM department",nativeQuery = true)
    public List<Department> getAllDepartments();

    @Query(value = "SELECT * FROM department WHERE dept_name=?1",nativeQuery = true)
    public Department getDeptCapacity(String name);
}
