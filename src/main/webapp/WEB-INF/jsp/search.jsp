<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<script>
    function validate(){
        var empid=document.SearchEmp.emp_id.value
        if(empid==null || empid==""){
            alert("Please enter employee id")
            return false;
        }
        if(isNaN(empid)){
            alert("Employee Id can only be numeric")
            return false;
        }
        return true;
    }
</script>
<body>
    <div class="container">
        <h1>Search Employee</h1>
        <div class="card">
            <div class="card-body">
                <form name="SearchEmp" action="/searchEmp" method="GET" onsubmit="return validate()">
                    <%--<label  class="col-sm-2 col-form-label">Employee Id</label>--%>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="emp_id" placeholder="Enter employee id">
                    </div><br>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
                <h4>${msg}</h4>
                <c:forEach items="${emps}" var="emp">
                    <label  class="col-sm-2 col-form-label">Employee Id : </label>
                    <label class="col-sm-2 col-form-label">${emp.emp_id}</label><br>
                    <label  class="col-sm-2 col-form-label">Employee Name : </label>
                    <label class="col-sm-2 col-form-label">${emp.emp_name}</label><br>
                    <label  class="col-sm-2 col-form-label">Employee Contact : </label>
                    <label class="col-sm-2 col-form-label">${emp.emp_contact}</label><br>
                    <label  class="col-sm-2 col-form-label">Employee Department : </label>
                    <label class="col-sm-2 col-form-label">${emp.emp_dept}</label><br>
                    <label  class="col-sm-2 col-form-label">Employee Gender : </label>
                    <label class="col-sm-2 col-form-label">${emp.emp_gender}</label><br>
                    <label  class="col-sm-2 col-form-label">Employee Image : </label>
                    <img src="data:image/jpeg;base64,${emp.image}" width="250" height="250"/><br>
                </c:forEach>
                <a href="/home">Home</a>
            </div>
        </div>
    </div>
</body>