<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Employee Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1>Employee Management System</h1>
        <div class="card">
            <div class="card-body">
                <form action="/register" method="GET">
                    <button type="submit" class="btn btn-primary">Create Employee</button>
                </form><br>
                <form action="/search" method="GET">
                    <button type="submit" class="btn btn-primary">Search Employee</button>
                </form><br>
                <form action="/modify" method="GET">
                    <button type="submit" class="btn btn-primary">Update Employee Details</button>
                </form><br>
                <form action="/showDelete" method="GET">
                    <button type="submit" class="btn btn-primary">Delete Employee</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>