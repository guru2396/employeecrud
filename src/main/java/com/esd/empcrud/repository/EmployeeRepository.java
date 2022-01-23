package com.esd.empcrud.repository;

import com.esd.empcrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(value = "SELECT * FROM employee WHERE emp_id=?1",nativeQuery = true)
    public Employee getEmployeeById(Integer emp_id);
}
