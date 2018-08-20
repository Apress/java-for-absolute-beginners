<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

        <html>
        <head>

        <title>Web Application Demo JSP Page</title>
        </head>

        <body bgcolor=black>
        <fmt:formatDate value="${requestScope.today}" pattern="dd/MM/yyyy" var="todayFormatted"/>
        <p style="color:#ffd200"> Today is <c:out value="${todayFormatted}" /> </p>
        </body>
        </html>