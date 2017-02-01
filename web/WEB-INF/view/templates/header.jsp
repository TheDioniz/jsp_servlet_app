<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- HEADER COMMENT -->
<html>
<head>
    <title>${param.title}</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />" />

    <style>
        body {
            /*background-color: lightgray;*/
        }
    </style>

</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/" />">Denisos</a>
        </div>
        <div class="container-fluid">
            <ul class="nav navbar-nav">
                <li><a href="#projects">Projects</a></li>
                <li><a href="#about">About Me</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div>
    </nav>
</div>