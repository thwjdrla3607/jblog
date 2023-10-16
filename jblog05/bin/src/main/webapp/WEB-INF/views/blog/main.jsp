<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<c:choose>
						<c:when test="${selected.equals('post')}">
							<h4>${postVo.title }</h4>
							<p>${postVo.contents }<p>
						</c:when>
						<c:when test="${selected.equals('category')}">
							<h4>${categoryVo.name }</h4>
							<p>${categoryVo.description }<p>
						</c:when>	
						<c:otherwise>
							<c:forEach items="${post_list }" var="vo" varStatus="status">
								<ul class="blog-list">
									<li><a
										href="${pageContext.request.contextPath }/${blogVo.blogId}/${vo.categoryNo }/${vo.no}">${vo.title }</a>
										<span>${vo.regDate }</span></li>
								</ul>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="${pageContext.request.contextPath}${blogVo.image }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<c:forEach items="${category_list }" var="vo" varStatus="status">
				<ul class="blog-list">
					<li><a
						href="${pageContext.request.contextPath }/${blogVo.blogId}/${vo.no }">${vo.name }</a>
						</li>
				</ul>
			</c:forEach>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>