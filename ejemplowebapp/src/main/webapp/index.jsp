<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<h2>Hola Mundo</h2>

<ul>
<c:forEach items="${datos}" var="dato">
<li>${dato}</li>
</c:forEach>
</ul>
</body>
</html>
