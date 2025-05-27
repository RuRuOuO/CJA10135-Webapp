<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.members.model.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
MembersVO membersVO = (MembersVO) request.getAttribute("membersVO"); //MembersServlet.java(Concroller), 存入req的membersVO物件
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料 - listOneMember.jsp</title>

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
	/* 	width: 600px; */
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>會員資料 - listOneMember.jsp</h3>
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
			<th>會員雜湊密碼</th>
			<th>會員密碼鹽值</th>
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
		<tr>
			<td><%=membersVO.getMemberId()%></td>
			<td><%=membersVO.getMemberEmail()%></td>
			<td><%=membersVO.getMemberPasswordHash()%></td>
			<td><%=membersVO.getMemberPasswordSalt()%></td>
			<td><%=membersVO.getMemberName()%></td>
			<td><%=membersVO.getMemberBirthdate()%></td>
			<td><%=membersVO.getMemberGender()%></td>
			<td><%=membersVO.getMemberPhone()%></td>
			<td><%=membersVO.getMemberAddress()%></td>
			<td><img
				src="${pageContext.request.contextPath}/members/members.do?action=showImage&memberId=${membersVO.memberId}"
				width="100"></td>
			<td><%=membersVO.getMemberCreatedAt()%></td>
			<td><%=membersVO.getMemberUpdatedAt()%></td>
			<td><%=membersVO.getMemberLastLoginTime()%></td>
			<td><%=membersVO.getMemberStatus()%></td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/members/members.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改"> <input type="hidden"
						name="memberId" value="${membersVO.memberId}"> <input
						type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/members/members.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="刪除"> <input type="hidden"
						name="memberId" value="${membersVO.memberId}"> <input
						type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</table>

</body>
</html>