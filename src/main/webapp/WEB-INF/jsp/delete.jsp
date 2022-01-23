<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>Delete Employee</h1>
        <div class="card">
            <div class="card-body">
                <form action="/delete" method="GET">
                    <%--<label  class="col-sm-2 col-form-label">Employee Id</label>--%>
                    <div class="col-sm-7">
                        <input type="text" class="form-control" name="emp_id" placeholder="Enter employee id">
                    </div><br>
                    <button type="submit" class="btn btn-primary">Delete</button>
                </form>
               <h4>${msg}</h4>
                <a href="/home">Home</a>
            </div>
        </div>
    </div>
</body>