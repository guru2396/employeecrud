<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<script>
    function validate(){
        var curr=document.updateForm.currEmpId.value
        var newEmp=document.updateForm.newEmpId.value
        var name=document.updateForm.emp_name.value
        var contact=document.updateForm.emp_contact.value
        if(curr==null || curr==""){
            alert("Please enter current employee Id")
            return false;
        }
        if(newEmp!=null && newEmp!=""){
            if(isNaN(newEmp)){
                alert("New Employee Id can only be numeric")
                return false;
            }
        }
        if(contact!=null && contact!=""){
            if(isNaN(contact)){
                alert("Contact can only be numeric")
                return false;
            }
            if(contact.length!=10){
                alert("Contact should be 10 digit number")
                return false;
            }
        }
        return true;
    }
</script>
<body>
    <div class="container">
        <h1>Update Employee Details</h1>
        <div class="card">
            <div class="card-body">
                <form name ="updateForm" enctype="multipart/form-data" method="POST" action="/update" onsubmit="return validate()">
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Current Employee Id</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="currEmpId" placeholder="Enter current employee id">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">New Employee Id</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="newEmpId" placeholder="Enter new employee id">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Name</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="emp_name" placeholder="Enter name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Contact</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" name="emp_contact" placeholder="Enter contact">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Department</label>
                        <div class="col-sm-7">
                            <select name="emp_dept">
                                <option value=""></option>
                                <c:forEach items="${depts}" var="dept">
                                    <option value="${dept.dept_name}">${dept.dept_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Gender</label>
                        <div class="col-sm-7">
                            <select name="emp_gender">
                                <option value=""></option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                                <option value="Others">Others</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Image</label>
                        <div class="col-sm-7">
                            <input type="file" class="form-control" name="image">
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <h4>${msg}</h4>
                </form>
                <a href="/home">Home</a>
            </div>
        </div>
    </div>
</body>