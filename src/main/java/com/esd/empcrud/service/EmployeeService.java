package com.esd.empcrud.service;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.esd.empcrud.config.AmazonClient;
import com.esd.empcrud.dto.EmployeeDto;
import com.esd.empcrud.dto.EmployeeResponseDto;
import com.esd.empcrud.dto.EmployeeUpdateDto;
import com.esd.empcrud.entity.Department;
import com.esd.empcrud.entity.Employee;
import com.esd.empcrud.repository.DepartmentRepository;
import com.esd.empcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Value("${aws.bucket}")
    private String bucketName;

    @Value("${aws.endpoint}")
    private String endpoint;

    @Autowired
    private AmazonClient amazonClient;

    public String addNewEmployee(EmployeeDto employeeDto) throws IOException {
        String msg="";
        Department dept =departmentRepository.getDeptCapacity(employeeDto.getEmp_dept());
        if(dept.getDept_capacity()==0){
            msg="No capacity in "+dept.getDept_name()+" department!";
            return msg;
        }
        Employee employee=new Employee();
        Random r=new Random();
        employee.setEmp_id(r.nextInt(2000000000));
        employee.setEmp_contact(employeeDto.getEmp_contact());
        employee.setEmp_dept(dept.getDept_id());
        employee.setEmp_isdeleted("N");
        employee.setEmp_name(employeeDto.getEmp_name());
        employee.setEmp_gender(employeeDto.getEmp_gender());
        MultipartFile img=employeeDto.getImage();
        File file=new File(img.getOriginalFilename());
        FileOutputStream fo=new FileOutputStream(file);
        fo.write(img.getBytes());
        fo.close();
        String fileName=new Date().getTime()+"-"+img.getOriginalFilename().replace(" ","_");
        String url=endpoint+"/"+bucketName+"/"+fileName;
        amazonClient.uploadFile(fileName,file);
        file.delete();
        employee.setEmp_imgurl(url);
        employeeRepository.save(employee);
        dept.setDept_capacity(dept.getDept_capacity()-1);
        departmentRepository.save(dept);
        msg="Employee registered with employee Id:"+employee.getEmp_id();
        return msg;
    }

    public String updateEmployee(EmployeeUpdateDto employeeUpdateDto){
        String msg="";
        Integer empid=Integer.parseInt(employeeUpdateDto.getCurrEmpId());
        Employee employee=employeeRepository.getEmployeeById(empid);
        if(employee==null || "Y".equals(employee.getEmp_isdeleted())){
            msg="Employee not found";
            return msg;
        }
        if(employeeUpdateDto.getNewEmpId()!=null && !"".equals(employeeUpdateDto.getNewEmpId())){
            Integer newEmpId=Integer.parseInt(employeeUpdateDto.getNewEmpId());
            Employee emp=employeeRepository.getEmployeeById(newEmpId);
            if(emp!=null){
                msg="Employee Id already exists";
                return msg;
            }
            employee.setEmp_id(newEmpId);
        }
        if(employeeUpdateDto.getEmp_dept()!=null && !"".equals(employeeUpdateDto.getEmp_dept())){
            Department dept =departmentRepository.getDeptCapacity(employeeUpdateDto.getEmp_dept());
            if(employee.getEmp_dept()!=dept.getDept_id()){
                if(dept.getDept_capacity()==0){
                    msg="No capacity in "+dept.getDept_name()+" department!";
                    return msg;
                }
                employee.setEmp_dept(dept.getDept_id());
                dept.setDept_capacity(dept.getDept_capacity()-1);
                departmentRepository.save(dept);
            }
        }
        if(employeeUpdateDto.getEmp_contact()!=null && !"".equals(employeeUpdateDto.getEmp_contact())){
            employee.setEmp_contact(employeeUpdateDto.getEmp_contact());
        }
        if(employeeUpdateDto.getEmp_name()!=null && !"".equals(employeeUpdateDto.getEmp_name())) {
            employee.setEmp_name(employeeUpdateDto.getEmp_name());
        }
        if(employeeUpdateDto.getEmp_gender()!=null && !"".equals(employeeUpdateDto.getEmp_gender())){
            employee.setEmp_gender(employeeUpdateDto.getEmp_gender());
        }
        if(employeeUpdateDto.getImage()!=null){
            MultipartFile img=employeeUpdateDto.getImage();
            File file=new File(img.getOriginalFilename());
            FileOutputStream fo= null;
            try {
                fo = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fo.write(img.getBytes());
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileName=new Date().getTime()+"-"+img.getOriginalFilename().replace(" ","_");
            String url=endpoint+"/"+bucketName+"/"+fileName;
            amazonClient.uploadFile(fileName,file);
            file.delete();
            employee.setEmp_imgurl(url);
        }
        employeeRepository.save(employee);
        msg="Employee Details updated";

        return msg;
    }

    public EmployeeResponseDto getEmployee(Integer emp_id) throws IOException {
        EmployeeResponseDto response=null;
        Employee emp=employeeRepository.getEmployeeById(emp_id);
        if(emp!=null && !"Y".equals(emp.getEmp_isdeleted())){
            response=new EmployeeResponseDto();
            String url=emp.getEmp_imgurl();
            String filename=url.substring(url.lastIndexOf("/")+1);
            System.out.print(filename);
            S3ObjectInputStream is=amazonClient.fetchFile(filename);
            ByteArrayOutputStream bo=new ByteArrayOutputStream();
            int bytes=-1;
            byte[] buffer=new byte[4096];
            while((bytes=is.read(buffer))!=-1){
                bo.write(buffer,0,bytes);
            }
            byte[] image=bo.toByteArray();
            String base64= Base64.getEncoder().encodeToString(image);
            is.close();
            bo.close();
            Department dept=departmentRepository.getById(emp.getEmp_dept());
            response.setEmp_id(emp.getEmp_id());
            response.setEmp_name(emp.getEmp_name());
            response.setEmp_contact(emp.getEmp_contact());
            response.setImage(base64);
            response.setEmp_gender(emp.getEmp_gender());
            response.setEmp_dept(dept.getDept_name());

        }

        return response;
    }

    public String deleteEmployee(Integer emp_id){
        Employee emp=employeeRepository.getEmployeeById(emp_id);
        String msg="";
        if(emp==null || "Y".equals(emp.getEmp_isdeleted())){
            msg="Employee Id not found!";
            return msg;
        }
        emp.setEmp_isdeleted("Y");
        employeeRepository.save(emp);
        msg="Employee deleted successfully";
        return msg;
    }
}
