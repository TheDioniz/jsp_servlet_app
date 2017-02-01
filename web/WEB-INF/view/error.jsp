<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="templates/header.jsp">
    <c:param name="title" value="Home Page"/>
</c:import>

<sql:setDataSource var="ds" dataSource="jdbc/webshop"/>
<sql:query var="results" dataSource="${ds}" sql="SELECT * FROM users"/>

<div class="container">
    <h2>Error occurred, please contact your administrator.</h2>
    <h3>Message:</h3>
    <p class="well">

        <c:out value='<%= request.getAttribute("message") %>' />
    </p>
</div>

<c:import url="templates/footer.jsp"/>
