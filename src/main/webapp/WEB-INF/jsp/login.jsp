<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Login</title></head>
<body>
<h2>Login</h2>
<c:if test="${not empty error}"><p style="color:red">${error}</p></c:if>
<form method="post" action="login.do">
  Email: <input name="email" type="email" required><br>
  Password: <input name="password" type="password" required><br>
  <button>Log in</button>
</form>
<p>Demo accounts: admin@groovygrails.in / admin123</p>
</body>
</html>
