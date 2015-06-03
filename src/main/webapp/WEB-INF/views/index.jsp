<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>${message}</h2>
<br>
<br>
<c:forEach items="${countries}" var="country">
   ${country}<p>
</c:forEach>
<br>
<br>
<c:forEach items="${countryList}" var="country">
   ${country.code} ${country.name}<p>
</c:forEach>
</body>
</html>
