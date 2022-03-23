<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<aside class="main-sidebar">
	<section class="sidebar">
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

				<c:if test="${sessionScope.user.userType=='学生'}">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/students/showInfo?id=${sessionScope.user.userId}"> <i
							class="fa fa-circle-o"></i> 个人信息查看
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/students/nowBorrow?id=${sessionScope.user.userId}"> <i
							class="fa fa-circle-o"></i> 当前借阅
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/students/findAllBook?page=1&size=4"> <i
							class="fa fa-circle-o"></i> 借阅书籍
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/students/history?id=${sessionScope.user.userId}"> <i
							class="fa fa-circle-o"></i> 借阅历史
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/students/findLate?id=${sessionScope.user.userId}"> <i
							class="fa fa-circle-o"></i> 超期图书
					</a></li>
					</c:if>
			<c:if test="${sessionScope.user.userType=='管理员'}">
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/students/findAll?page=1&size=4"> <i
							class="fa fa-circle-o"></i> 学生管理
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/books/findAll?page=1&size=4"> <i
							class="fa fa-circle-o"></i> 图书管理
					</a></li>
				<li id="system-setting"><a
						href="${pageContext.request.contextPath}/stuAndBook/findAll?page=1&size=5"> <i
						class="fa fa-circle-o"></i> 借阅信息
				</a></li>

				<li id="system-setting"><a
						href="${pageContext.request.contextPath}/admin/findAll?page=1&size=5"> <i
						class="fa fa-circle-o"></i> 添加管理员
				</a></li>
			</c:if>
		</ul>

	</section>
	<!-- /.sidebar -->
</aside>