package com.members.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

import com.members.model.*;

@MultipartConfig
public class MembersServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("memberId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer memberId = null;
			try {
				memberId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			MembersService membersSvc = new MembersService();
			MembersVO membersVO = membersSvc.getOneMember(memberId);
			if (membersVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/members/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("membersVO", membersVO); // 資料庫取出的membersVO物件,存入req
			String url = "/members/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllMembers.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));

			/*************************** 2.開始查詢資料 ****************************************/
			MembersService membersSvc = new MembersService();
			MembersVO membersVO = membersSvc.getOneMember(memberId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("membersVO", membersVO); // 資料庫取出的membersVO物件,存入req
			String url = "/members/update_members_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_members_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_members_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 1.會員編號
			Integer memberId = Integer.valueOf(req.getParameter("memberId").trim());

			// 2.會員信箱
			String memberEmail = req.getParameter("memberEmail").trim();
			if (memberEmail == null || memberEmail.trim().length() == 0) {
				errorMsgs.add("會員信箱請勿空白");
			}

			// 3.會員雜湊密碼
			String memberPasswordHash = req.getParameter("memberPasswordHash").trim();
			if (memberPasswordHash == null || memberPasswordHash.trim().length() == 0) {
				errorMsgs.add("會員雜湊密碼請勿空白");
			}

			// 4.會員密碼鹽值
			String memberPasswordSalt = req.getParameter("memberPasswordSalt").trim();
			if (memberPasswordSalt == null || memberPasswordSalt.trim().length() == 0) {
				errorMsgs.add("會員密碼鹽值請勿空白");
			}

			// 5.會員姓名
			String memberName = req.getParameter("memberName");
			String memberNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if (memberName == null || memberName.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!memberName.trim().matches(memberNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
			}

			// 6.會員生日
			java.sql.Date memberBirthdate = null;
			try {
				System.out.println(req.getParameter("memberBirthdate"));
				memberBirthdate = java.sql.Date.valueOf(req.getParameter("memberBirthdate").trim());
			} catch (IllegalArgumentException e) {
				memberBirthdate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			// 7.會員性別
			Integer memberGender = Integer.valueOf(req.getParameter("memberGender").trim());

			// 8.會員電話
			String memberPhone = req.getParameter("memberPhone");
//				String memberPhoneReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{20}$";
			if (memberPhone == null || memberPhone.trim().length() == 0) {
				errorMsgs.add("會員電話: 請勿空白");
			} // else if(!memberName.trim().matches(memberNameReg)) {
				// //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
//	            }

			// 9.會員地址
			String memberAddress = req.getParameter("memberAddress");
//				String memberPhoneReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{20}$";
			if (memberAddress == null || memberAddress.trim().length() == 0) {
				errorMsgs.add("會員地址: 請勿空白");
			}

			// 10.會員照片
			byte[] memberPhoto = null;
			Part part = req.getPart("memberPhoto");
			InputStream is = part.getInputStream();
			memberPhoto = is.readAllBytes();
			is.close();
			

			// 11.會員建立日期
			Timestamp memberCreatedAt = null;
			try {
				memberCreatedAt = Timestamp.valueOf(req.getParameter("memberCreatedAt").trim());
			} catch (IllegalArgumentException e) {
				memberCreatedAt = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			// 12.會員更新日期
			Timestamp memberUpdatedAt = null;
			try {
				memberUpdatedAt = Timestamp.valueOf(req.getParameter("memberUpdatedAt").trim());
			} catch (IllegalArgumentException e) {
				memberUpdatedAt = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			// 13.會員最後登入時間
			Timestamp memberLastLoginTime = null;
			try {
				memberLastLoginTime = Timestamp.valueOf(req.getParameter("memberLastLoginTime").trim());
			} catch (IllegalArgumentException e) {
				memberLastLoginTime = new Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			// 14.會員狀態
			Integer memberStatus = Integer.valueOf(req.getParameter("memberStatus").trim());

			MembersVO membersVO = new MembersVO();
			membersVO.setMemberId(memberId);
			membersVO.setMemberEmail(memberEmail);
			membersVO.setMemberPasswordHash(memberPasswordHash);
			membersVO.setMemberPasswordSalt(memberPasswordSalt);
			membersVO.setMemberName(memberName);
			membersVO.setMemberBirthdate(memberBirthdate);
			membersVO.setMemberGender(memberGender);
			membersVO.setMemberPhone(memberPhone);
			membersVO.setMemberAddress(memberAddress);
			membersVO.setMemberPhoto(memberPhoto);
			membersVO.setMemberCreatedAt(memberCreatedAt);
			membersVO.setMemberUpdatedAt(memberUpdatedAt);
			membersVO.setMemberLastLoginTime(memberLastLoginTime);
			membersVO.setMemberStatus(memberStatus);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("membersVO", membersVO); // 含有輸入格式錯誤的membersVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/members/update_members_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MembersService membersSvc = new MembersService();
			membersVO = membersSvc.updateMembers(memberId, memberEmail, memberPasswordHash, memberPasswordSalt,
					memberName, memberBirthdate, memberGender, memberPhone, memberAddress, memberPhoto, memberCreatedAt,
					memberUpdatedAt, memberLastLoginTime, memberStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("membersVO", membersVO); // 資料庫update成功後,正確的的membersVO物件,存入req
			String url = "/members/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// 2.會員信箱
			String memberEmail = req.getParameter("memberEmail").trim();
			if (memberEmail == null || memberEmail.trim().length() == 0) {
				errorMsgs.add("會員信箱請勿空白");
			}

			// 3.會員雜湊密碼
			String memberPasswordHash = req.getParameter("memberPasswordHash").trim();
			if (memberPasswordHash == null || memberPasswordHash.trim().length() == 0) {
				errorMsgs.add("會員雜湊密碼請勿空白");
			}

			// 4.會員密碼鹽值
			String memberPasswordSalt = req.getParameter("memberPasswordSalt").trim();
			if (memberPasswordSalt == null || memberPasswordSalt.trim().length() == 0) {
				errorMsgs.add("會員密碼鹽值請勿空白");
			}

			// 5.會員姓名
			String memberName = req.getParameter("memberName");
			String memberNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if (memberName == null || memberName.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!memberName.trim().matches(memberNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
			}

			// 6.會員生日
			java.sql.Date memberBirthdate = null;
			try {
				memberBirthdate = java.sql.Date.valueOf(req.getParameter("memberBirthdate").trim());
			} catch (IllegalArgumentException e) {
				memberBirthdate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			// 7.會員性別
			Integer memberGender = Integer.valueOf(req.getParameter("memberGender").trim());

			// 8.會員電話
			String memberPhone = req.getParameter("memberPhone");
//			String memberPhoneReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{20}$";
			if (memberPhone == null || memberPhone.trim().length() == 0) {
				errorMsgs.add("會員電話: 請勿空白");
			} // else if(!memberName.trim().matches(memberNameReg)) {
				// //以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到30之間");
//            }

			// 9.會員地址
			String memberAddress = req.getParameter("memberAddress");
//			String memberPhoneReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{20}$";
			if (memberAddress == null || memberAddress.trim().length() == 0) {
				errorMsgs.add("會員地址: 請勿空白");
			}

			// 10.會員照片
			byte[] memberPhoto = null;
			Part part = req.getPart("memberPhoto");
			InputStream is = part.getInputStream();
			memberPhoto = is.readAllBytes();
			is.close();

			// 11.會員建立日期
//			Timestamp memberCreatedAt = null;
//			try {
//				memberCreatedAt = Timestamp.valueOf(req.getParameter("memberCreatedAt").trim());
//			} catch (IllegalArgumentException e) {
//				memberCreatedAt = new Timestamp(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
			Timestamp memberCreatedAt = new Timestamp(System.currentTimeMillis());

			// 12.會員更新日期
//			Timestamp memberUpdatedAt = null;
//			try {
//				memberUpdatedAt = Timestamp.valueOf(req.getParameter("memberUpdatedAt").trim());
//			} catch (IllegalArgumentException e) {
//				memberUpdatedAt = new Timestamp(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
			Timestamp memberUpdatedAt = new Timestamp(System.currentTimeMillis());
			

			// 13.會員最後登入時間
//			Timestamp memberLastLoginTime = null;
//			try {
//				memberLastLoginTime = Timestamp.valueOf(req.getParameter("memberLastLoginTime").trim());
//			} catch (IllegalArgumentException e) {
//				memberLastLoginTime = new Timestamp(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期!");
//			}
			Timestamp memberLastLoginTime = new Timestamp(System.currentTimeMillis());

			// 14.會員狀態
//			Integer memberStatus = Integer.valueOf(req.getParameter("memberStatus").trim());
			Integer memberStatus = 0;

			MembersVO membersVO = new MembersVO();
			membersVO.setMemberEmail(memberEmail);
			membersVO.setMemberPasswordHash(memberPasswordHash);
			membersVO.setMemberPasswordSalt(memberPasswordSalt);
			membersVO.setMemberName(memberName);
			membersVO.setMemberBirthdate(memberBirthdate);
			membersVO.setMemberGender(memberGender);
			membersVO.setMemberPhone(memberPhone);
			membersVO.setMemberAddress(memberAddress);
			membersVO.setMemberPhoto(memberPhoto);
			membersVO.setMemberCreatedAt(memberCreatedAt);
			membersVO.setMemberUpdatedAt(memberUpdatedAt);
			membersVO.setMemberLastLoginTime(memberLastLoginTime);
			membersVO.setMemberStatus(memberStatus);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("membersVO", membersVO); // 含有輸入格式錯誤的membersVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/members/addMember.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			MembersService membersSvc = new MembersService();
			membersVO = membersSvc.addMember(memberEmail, memberPasswordHash, memberPasswordSalt, memberName,
					memberBirthdate, memberGender, memberPhone, memberAddress, memberPhoto, memberCreatedAt,
					memberUpdatedAt, memberLastLoginTime, memberStatus);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/members/listAllMembers.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMembers.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllMembers.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));

			/*************************** 2.開始刪除資料 ***************************************/
			MembersService membersSvc = new MembersService();
			membersSvc.deleteMembers(memberId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/members/listAllMembers.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
		/******************** ？？？、顯示圖片 ********************/
		if ("showImage".equals(action)) {
			byte[] imageBytes = null;
			Integer memberId = Integer.valueOf(req.getParameter("memberId"));
			MembersService membersSvc = new MembersService();
			MembersVO newsVO = membersSvc.getOneMember(memberId);

			imageBytes = newsVO.getMemberPhoto();

			if (imageBytes != null) {
				res.setContentType("image/jpeg"); // 或根據實際圖片格式設置 MIME 類型
				ServletOutputStream out = res.getOutputStream();
				out.write(imageBytes);
				out.flush();
				out.close();
			}
			return;
		}
	}
}
