<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 页面头部 -->
<header class="main-header">
	<!-- Logo -->
	<a href="#" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
		<span class="logo-mini"><b>图书</b></span> <!-- logo for regular state and mobile devices -->
		<span class="logo-lg"><b>图书</b>管理系统</span>
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top">
		<!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a>

		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">

				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown">
							${sessionScope.user.userType}：${sessionScope.user.userName}
				</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<!-- Menu Footer-->
						<li class="user-footer">
							<c:if test="${sessionScope.user.userType=='学生'}">
							<div class="pull-left">

								<a href="${pageContext.request.contextPath}/pages/student/update-password.jsp" class="btn btn-default btn-flat">修改密码</a>
							</div>
							<div class="pull-right">
								<a href="${pageContext.request.contextPath}/user/logout"
									class="btn btn-default btn-flat">注销</a>
							</div>
							</c:if>
							<c:if test="${sessionScope.user.userType=='管理员'}">
								<div class="pull-right">
									<a href="${pageContext.request.contextPath}/user/logout"
									   class="btn btn-default btn-flat">注销</a>
								</div>
							</c:if>
						</li>
					</ul></li>

			</ul>
		</div>
	</nav>
</header>
<!-- 页面头部 /-->