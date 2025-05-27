package com.members.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MembersService {

	private MembersDAO_interface dao;

	public MembersService() {
		dao = new MembersDAO();
	}

	public MembersVO addMember(String memberEmail, String memberPasswordHash, String memberPasswordSalt,
			String memberName, Date memberBirthdate, Integer memberGender, String memberPhone, String memberAddress,
			byte[] memberPhoto, Timestamp memberCreatedAt, Timestamp memberUpdatedAt, Timestamp memberLastLoginTime,
			Integer memberStatus) {
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
		dao.insert(membersVO);

		return membersVO;
	}

	public MembersVO updateMembers(Integer memberId, String memberEmail, String memberPasswordHash,
			String memberPasswordSalt, String memberName, Date memberBirthdate, Integer memberGender,
			String memberPhone, String memberAddress, byte[] memberPhoto, Timestamp memberCreatedAt, Timestamp memberUpdatedAt,
			Timestamp memberLastLoginTime, Integer memberStatus) {

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
		dao.update(membersVO);

		return membersVO;
	}

	public void deleteMembers(Integer memberId) {
		dao.delete(memberId);
	}

	public MembersVO getOneMember(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}

	public List<MembersVO> getAll() {
		return dao.getAll();
	}
}
