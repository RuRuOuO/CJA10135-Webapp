package com.members.model;

public class Test {
	public static void main(String[] args) {
		MembersDAO membersDAO = new MembersDAO();
//		MembersVO membersVO = new MembersVO();
		membersDAO.getAll();
	}
}
