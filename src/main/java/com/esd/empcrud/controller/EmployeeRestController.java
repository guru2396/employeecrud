package com.esd.empcrud.controller;

import com.esd.empcrud.dto.EmployeeDto;
import com.esd.empcrud.dto.EmployeeTempDto;
import com.esd.empcrud.entity.Department;
import com.esd.empcrud.repository.DepartmentRepository;
import com.esd.empcrud.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value="/depts")
    public ResponseEntity<List<Department>> getDepts(){
        List<Department> depts=departmentRepository.getAllDepartments();
        return new ResponseEntity<>(depts,HttpStatus.OK);
    }

    @PostMapping(value="/addEmp",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> createEmployee(@RequestPart("emp") String emp,@RequestPart("img") MultipartFile image) throws IOException {
        ObjectMapper mapper=new ObjectMapper();
        EmployeeTempDto employeeTempDto=mapper.readValue(emp,EmployeeTempDto.class);
        EmployeeDto employeeDto=new EmployeeDto();
        employeeDto.setEmp_name(employeeTempDto.getEmp_name());
        employeeDto.setEmp_contact(employeeTempDto.getEmp_contact());
        employeeDto.setEmp_dept(employeeTempDto.getEmp_dept());
        employeeDto.setImage(image);
        System.out.print(employeeDto.getEmp_name());
        String msg=employeeService.addNewEmployee(employeeDto);
        ResponseEntity<Integer> r=new ResponseEntity<>(10,HttpStatus.OK);
        System.out.println(r);
        return r;
    }
}
