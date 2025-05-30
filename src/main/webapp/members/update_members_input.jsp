<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>

<%
//見com.members.controller.MembersServlet.java第163行存入req的membersVO物件 (此為從資料庫取出的membersVO, 也可以是輸入格式有錯誤時的membersVO物件)
MembersVO membersVO = (MembersVO) request.getAttribute("membersVO");
%>
--<%=membersVO == null%>--${membersVO.memberId}--
<!-- for line 100 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料修改 - update_members_input.jsp</title>

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
				<h3>會員資料修改 - update_members_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

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
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=membersVO.getMemberId()%></td>
			</tr>
			<tr>
				<td>會員信箱:</td>
				<td><input type="TEXT" name="memberEmail"
					value="<%=membersVO.getMemberEmail()%>" size="45" /></td>
			</tr>
			<tr>
				<td>會員雜湊密碼:</td>
				<td><input type="TEXT" name="memberPasswordHash"
					value="<%=membersVO.getMemberPasswordHash()%>" size="45" /></td>
			</tr>
			<tr>
				<td>會員密碼鹽值:</td>
				<td><input type="TEXT" name="memberPasswordSalt"
					value="<%=membersVO.getMemberPasswordSalt()%>" size="45" /></td>
			</tr>
			<tr>
				<td>會員姓名:</td>
				<td><input type="TEXT" name="memberName"
					value="<%=membersVO.getMemberName()%>" size="45" /></td>
			</tr>
			<tr>
				<td>會員生日:</td>
<%-- 				<td><input name="memberBirthdate" value="<%=membersVO.getMemberBirthdate()%>" id="f_date1" type="text"></td> --%>
				<td><input type="TEXT" name="memberBirthdate" value="<%=membersVO.getMemberBirthdate()%>"></td>
			</tr>
			<tr>
				<td>會員性別:</td>
						<td><input type="TEXT" name="memberGender" value="<%=membersVO.getMemberGender()%>" size="45"/></td>
<!-- 				<td><input type="RADIO" id="0" name="memberGender" value=0 -->
<%-- 					<%="0".equals(request.getParameter("memberGender")) ? "checked" : ""%> --%>
<!-- 					size="45" /> <label for="0">男</label> <input type="RADIO" id="1" -->
<!-- 					name="memberGender" value=1 -->
<%-- 					<%="1".equals(request.getParameter("memberGender")) ? "checked" : ""%> --%>
<!-- 					size="45" /> <label for="1">女</label></td> -->
<!-- 				<td><input type="RADIO" id="0" name="memberGender" value=0 -->
<%-- 					<%=membersVO.getMemberGender()==0 ? "checked" : ""%> --%>
<!-- 					size="45" /> <label for="0">男</label> <input type="RADIO" id="1" -->
<!-- 					name="memberGender" value=1 -->
<%-- 					<%=membersVO.getMemberGender()==1 ? "checked" : ""%> --%>
<!-- 					size="45" /> <label for="1">女</label></td> -->
			</tr>
			<tr>
				<td>會員電話:</td>
				<td><input type="TEXT" name="memberPhone"
					value="<%=membersVO.getMemberPhone()%>" size="45" /></td>
			</tr>
			<tr>
				<td>會員地址:</td>
				<td><input type="TEXT" name="memberAddress"
					value="<%=membersVO.getMemberAddress()%>" size="45" /></td>
			</tr>
			<tr>
				<!-- 		<td>目前照片:</td> -->
				<%-- 		<td><input type="TEXT" name="memberPhoto" value="<%=membersVO.getMemberPhoto()%>" size="45"/></td> --%>
				<td>目前照片:</td>
				<td><img
					src="<%=request.getContextPath()%>/members/ShowImage?memberId=<%=membersVO.getMemberId()%>"
					width="200" height="150" /></td>
				<td>上傳照片:</td>
				<td><input type="file" name="memberPhoto"
					value="<%=membersVO.getMemberPhoto()%>" size="45" /></td>
			</tr>
			<tr>
				<td>會員建立日期:</td>
<!-- 				<td><input name="memberCreatedAt" id="f_date2" type="text" disabled></td> -->
				<td><input type="TEXT" name="memberCreatedAt" value="<%=membersVO.getMemberCreatedAt()%>" disabled></td>
			</tr>
			<tr>
				<td>會員更新日期:</td>
<!-- 				<td><input name="memberUpdatedAt" id="f_date3" type="text" disabled></td> -->
				<td><input type="TEXT" name="memberUpdatedAt" value="<%=membersVO.getMemberUpdatedAt()%>" disabled></td>
			</tr>
			<tr>
				<td>最後登入時間:</td>
<!-- 				<td><input name="memberLastLoginTime" id="f_date4" type="text" disabled></td> -->
				<td><input name="memberLastLoginTime" value="<%=membersVO.getMemberLastLoginTime()%>" type="text" disabled></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>會員狀態:</td> -->
<!-- 				<td><input type="TEXT" name="memberStatus" -->
<%-- 					value="<%=membersVO.getMemberStatus()%>" size="45" /></td> --%>
<!-- 			</tr> -->

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input type="hidden" name="memberId" value="<%=membersVO.getMemberId()%>">
<%-- 		<br> <input type="hidden" name="action" value="update"> <input type="hidden" name="memberCreatedAt" value="<%=membersVO.getMemberCreatedAt()%> --%>
		<input type="submit" value="送出修改">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=membersVO.getMemberBirthdate()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
//         $.datetimepicker.setLocale('zh');
//         $('#f_date2').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=membersVO.getMemberCreatedAt()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
//         $.datetimepicker.setLocale('zh');
//         $('#f_date3').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=membersVO.getMemberUpdatedAt()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
//         $.datetimepicker.setLocale('zh');
//         $('#f_date4').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=membersVO.getMemberLastLoginTime()%> --%>
// 	', // value:   new Date(),
// 	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
// 	//startDate:	            '2017/07/10',  // 起始日
// 	//minDate:               '-1970-01-01', // 去除今日(不含)之前
// 	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
// 	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>