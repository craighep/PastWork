<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="content">
            <div style="text-align: center;" class="halfStyle">
                <h1>Login</h1>
                <form action="Login" method="post">
                    <h6>Username:</h6>
                    <input class="input" type="text" size="20" name="username">
                    <h6>Password:</h6>
                    <input class="input" type="password" size="20" name="password">
                    <p><input style="margin-right: -10px;" class="button1" type="submit" value="Login"></p>
                </form>
            </div>  
            <div style="text-align: center;" class="halfStyle">
                <h1>Register</h1>
                <form action="Register" method="post">
                    <h6>Username:</h6>
                    <input class="input" type="text" size="20" name="username">
                    <h6>Full name:</h6>
                    <input class="input" type="text" size="20" name="name">
                    <h6>Password:</h6>
                    <input class="input" type="password" size="20" name="password">
                    <p><input style="margin-left: -15px;" class="button1" type="submit" value="Register"></p>
                </form> 
            </div>
        </div>

    <c:choose>
        <c:when test='${result}'>
            <h2 style="color: green;">Successfully registered, please login</h2>
        </c:when><c:otherwise>
            <h2 style="color: red;">${error}</h2>
        </c:otherwise>
    </c:choose>

</body>
</html>