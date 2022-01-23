package com.esd.empcrud.controller;

import com.esd.empcrud.dto.EmployeeDto;
import com.esd.empcrud.dto.EmployeeResponseDto;
import com.esd.empcrud.dto.EmployeeUpdateDto;
import com.esd.empcrud.entity.Department;
import com.esd.empcrud.entity.Employee;
import com.esd.empcrud.repository.DepartmentRepository;
import com.esd.empcrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping(value="/home")
    public String displayHome(ModelMap modelMap){
        return "home";
    }

    @GetMapping(value="/register")
    public String displayRegister(ModelMap modelMap){
        List<Department> depts=departmentRepository.getAllDepartments();
        modelMap.put("depts",depts);
        return "index";
    }

    @GetMapping(value="/search")
    public String displaySearch(ModelMap modelMap){
        return "search";
    }

    @GetMapping(value="/modify")
    public String displayUpdate(ModelMap modelMap){
        List<Department> depts=departmentRepository.getAllDepartments();
        modelMap.put("depts",depts);
        return "update";
    }

    @PostMapping(value="/add")
    public String addEmployee(ModelMap modelMap,@ModelAttribute("employeeDto") EmployeeDto employeeDto) throws IOException {
        System.out.println("Dept:"+employeeDto.getEmp_dept());
        System.out.println("Dept:"+employeeDto.getEmp_name());
        String msg=employeeService.addNewEmployee(employeeDto);
        modelMap.put("msg",msg);
        List<Department> depts=departmentRepository.getAllDepartments();
        modelMap.put("depts",depts);
        return "index";
    }

    @PostMapping(value="/update")
    public String updateEmployee(ModelMap modelMap, @ModelAttribute EmployeeUpdateDto employeeUpdateDto){
        String msg=employeeService.updateEmployee(employeeUpdateDto);
        modelMap.put("msg",msg);
        List<Department> depts=departmentRepository.getAllDepartments();
        modelMap.put("depts",depts);
        return "update";
    }

    @GetMapping(value="/searchEmp")
    public String searchEmployee(ModelMap modelMap,@RequestParam String emp_id) throws IOException {
        String msg="";
        EmployeeResponseDto emp= employeeService.getEmployee(Integer.parseInt(emp_id));
        if(emp==null){
            msg="Employee not found";
        }
        else{
            List<EmployeeResponseDto> list=new ArrayList<>();
            list.add(emp);
            modelMap.put("emps",list);
        }
        modelMap.put("msg",msg);
        return "search";
    }

    @GetMapping(value = "/showDelete")
    public String displayDelete(ModelMap modelMap){
        return "delete";
    }

    @GetMapping(value="/delete")
    public String deleteEmployee(ModelMap modelMap,@RequestParam String emp_id){
        String msg="";
        Integer empId=Integer.parseInt(emp_id);
        msg=employeeService.deleteEmployee(empId);
        modelMap.put("msg",msg);
        return "delete";
    }
}
