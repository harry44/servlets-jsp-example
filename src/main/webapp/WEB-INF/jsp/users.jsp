<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Users</title></head>
<body>
<h2>Users (logged in as ${sessionScope.user})</h2>
<table border="1">
  <tr><th>Name</th><th>Email</th></tr>
  <c:forEach var="u" items="${users}">
    <tr><td>${u.name}</td><td>${u.email}</td></tr>
  </c:forEach>
</table>
<p><a href="logout.do">Log out</a></p>
</body>
</html>
