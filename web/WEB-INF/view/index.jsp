<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<c:import url="templates/header.jsp">
    <c:param name="title" value="Home Page"/>
</c:import>

<sql:setDataSource var="ds" dataSource="jdbc/webshop"/>
<sql:query var="results" dataSource="${ds}" sql="SELECT * FROM users"/>
<div class="container">
    <div class="col-lg-6">
        <table class="table table-bordered table-stripped table-hover table-condensed ">
            <thead>
            <tr class="warning">
                <th class="text-center">ID</th>
                <th class="text-center">Email</th>
                <th class="text-center">Password</th>
            </tr>
            </thead>
            <tbody class="text-center">
            <c:forEach var="result" items="${results.rows}" varStatus="status">
                <tr>
                    <td><c:out value="${result.id}"/></td>
                    <td><c:out value="${result.email}"/></td>
                    <td><c:out value="${result.password} "/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <form method="POST" action="<c:out value="controller" />">
        <div class="form-group col-lg-6">
            <p class="lead">Insert new data</p>
            <input type="hidden" name="action" value="insertUser"/>
            <input type="text" name="email" class="form-control" placeholder="Enter your email"/>
            <input type="password" name="password" class="form-control" placeholder="Enter your password"/>
            <a href="<c:out value=""/>"><input type="submit" class="btn btn-primary pull-right" value="Put to DB"
                                               style="margin-top: 10px"/></a>

        </div>
    </form>

    <div class="col-lg-12">
        <div class="row col-lg-6">
            <sql:query var="result" dataSource="${ds}" sql="SELECT * FROM users"/>
            <%--<c:set var="count" value="${result.rowCount}"/> --%>
            <%--<c:out value="${count}"/>--%>

            <form method="POST" action="<c:out value="controller" />" class="form-inline">
                <div class="form-group">

                    <input type="hidden" name="action" value="deleteUser" />

                    <p class="well">Delete row from DB by ID:</p>

                    <p style="font-size: 20px">Index number:</p>
                    <select name="id" class="form-control">
                        <c:forEach var="item" items="${result.rows}">
                            <option><c:out value="${item.id}" /></option>
                        </c:forEach>
                    </select>

                    <input type="submit" class="btn btn-danger" value="Delete" />

                </div>
            </form>
        </div>
    </div>


</div>

<c:import url="templates/footer.jsp"/>
