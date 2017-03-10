<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>View</title>
 <script src="./resources/js/jquery.js"></script>
 </head>

<div>
<div id="empDetail"></div>
</div>
Currently Added Employee:<br/>
Id : ${employee.id} <br/>
Name : ${employee.name} <br/>
<a href="addEmployee" >Add Employee</a><br/>
<a href="deleteEmployee" >Delete Employee</a>


</body>
</html>