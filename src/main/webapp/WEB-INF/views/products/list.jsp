<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;
charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>Titulo</td>
			<td>Valores</td>
		</tr>
		
		<!-- Loop -->
		<c:forEach items="${products}" var="product">
		
			<tr>
				<td>${product.title}</td>
				<td>
					<c:forEach items="${product.prices}" var="price">
						[${price.value} - ${price.bookType}]
					</c:forEach>
				</td>
			</tr>
		
		</c:forEach>
		<!-- Fim Loop -->
		
	</table>
</body>
</html>