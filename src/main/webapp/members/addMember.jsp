<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>

<%
//見com.members.controller.MembersServlet.java第238行存入req的membersVO物件 (此為輸入格式有錯誤時的membersVO物件)
MembersVO membersVO = (MembersVO) request.getAttribute("membersVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料新增 - addMember.jsp</title>

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
	width: 50%;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料新增 - addMembers.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="members.do" name="form1"
		enctype="multipart/form-data">
		<table>



			<tr>
				<td>會員信箱:</td>
				<td><input type="TEXT" name="memberEmail"
					value="<%=(membersVO == null) ? "test@aaa.com" : membersVO.getMemberEmail()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>會員雜湊密碼:</td>
				<td><input type="TEXT" name="memberPasswordHash"
					value="<%=(membersVO == null) ? "password_hash" : membersVO.getMemberPasswordHash()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>會員密碼鹽值:</td>
				<td><input type="TEXT" name="memberPasswordSalt"
					value="<%=(membersVO == null) ? "password_salt" : membersVO.getMemberPasswordSalt()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="memberName"
					value="<%=(membersVO == null) ? "測試用" : membersVO.getMemberName()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>會員生日:</td>
				<td><input name="memberBirthdate" id="f_date1" type="text"></td>
			</tr>
			<tr>
				<td>會員性別:</td>
				<!-- 				<td><input type="TEXT" name="memberGender" -->
				<%-- 					value="<%=(membersVO == null) ? "" : membersVO.getMemberGender()%>" --%>
				<!-- 					size="45" /></td> -->
				<td><input type="RADIO" id="0" name="memberGender" value=0
					<%="0".equals(request.getParameter("memberGender")) ? "checked" : ""%>
					size="45" /> <label for="0">男</label> <input type="RADIO" id="1"
					name="memberGender" value=1
					<%="1".equals(request.getParameter("memberGender")) ? "checked" : ""%>
					size="45" /> <label for="1">女</label></td>
			</tr>
			<tr>
				<td>會員電話:</td>
				<td><input type="TEXT" name="memberPhone"
					value="<%=(membersVO == null) ? "" : membersVO.getMemberPhone()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT" name="memberAddress"
					value="<%=(membersVO == null) ? "" : membersVO.getMemberAddress()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>會員照片:</td>
				<td><input type="TEXT" name="memberPhoto"
					value="<%=(membersVO == null) ? "" : membersVO.getMemberPhoto()%>"
					size="45" /></td>
			</tr>
			<!-- 			<tr> -->
			<!-- 				<td>會員建立日期:</td> -->
			<!-- 				<td><input name="memberCreatedAt" id="f_date2" type="text"></td> -->
			<!-- 			</tr> -->
			<!-- 			<tr> -->
			<!-- 				<td>會員更新日期:</td> -->
			<!-- 				<td><input name="memberUpdatedAt" id="f_date3" type="text"></td> -->
			<!-- 			</tr> -->
			<!-- 			<tr> -->
			<!-- 				<td>最後登入時間:</td> -->
			<!-- 				<td><input name="memberLastLoginTime" id="f_date4" type="text"></td> -->
			<!-- 			</tr> -->
			<!-- 			<tr> -->
			<!-- 				<td>會員狀態:</td> -->
			<!-- 				<td><input type="TEXT" name="memberStatus" -->
			<%-- 					value="<%=(membersVO == null) ? "" : membersVO.getMemberStatus()%>" --%>
			<!-- 					size="45" /></td> -->
			<!-- 			</tr> -->


			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

</body>
</html>