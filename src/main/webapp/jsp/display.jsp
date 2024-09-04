<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<ul>
    <c:forEach var="user" items="${users}">
        <li><c:out value="${user.userName}"/></li>
    </c:forEach>
</ul>

<a href="search.jsp">Search</a>

</body>
</html>
