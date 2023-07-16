<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>

<html>
<body>
<h2>Log-in</h2>
<div>
    <form method="POST" action="login">
        <label for="login">Login</label>
        <input type="text" id="login" name="login"><br />
        <label for="senha">Senha</label>
        <input type="password" id="senha" name="senha">
        <br />
        <br />
        <button type="submit">Autenticar</button>
        <c:if test="${not empty errorMessage}">
            <h2>${errorMessage}</h2>
        </c:if>
    </form>
    <a href="registrar.jsp">Registrar</a>
</div>
</body>
</html>
