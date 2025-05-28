<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.members.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
MembersService membersSvc = new MembersService();
List<MembersVO> list = membersSvc.getAll();
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有會員資料 - listAllMembers.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有會員資料 - listAllMembers.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>會員編號</th>
			<th>會員信箱</th>
<!-- 			<th>會員雜湊密碼</th> -->
<!-- 			<th>會員密碼鹽值</th> -->
			<th>會員姓名</th>
			<th>會員生日</th>
			<th>會員性別</th>
			<th>會員電話</th>
			<th>會員地址</th>
			<th>會員照片</th>
			<th>會員建立日期</th>
			<th>會員更新日期</th>
			<th>會員最後登入時間</th>
			<th>會員狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="membersVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${membersVO.memberId}</td>
				<td>${membersVO.memberEmail}</td>
<%-- 				<td>${membersVO.memberPasswordHash}</td> --%>
<%-- 				<td>${membersVO.memberPasswordSalt}</td> --%>
				<td>${membersVO.memberName}</td>
				<td>${membersVO.memberBirthdate}</td>
				<td>${membersVO.memberGender==0 ? "男" : "女"}</td>
				<td>${membersVO.memberPhone}</td>
				<td>${membersVO.memberAddress}</td>
				<td><img src="${pageContext.request.contextPath}/members/members.do?action=showImage&memberId=${membersVO.memberId}" width="100"></td>
				<td>${membersVO.memberCreatedAt}</td>
				<td>${membersVO.memberUpdatedAt}</td>
				<td>${membersVO.memberLastLoginTime}</td>
				<td>${membersVO.memberStatus==0 ? "未驗證" : "已驗證"}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/members/members.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="memberId" value="${membersVO.memberId}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/members/members.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="memberId" value="${membersVO.memberId}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>