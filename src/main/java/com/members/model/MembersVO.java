package com.members.model;

import java.sql.Date;
import java.sql.Timestamp;

public class MembersVO implements java.io.Serializable {
	private Integer memberId;
	private String memberEmail;
	private String memberPasswordHash;
	private String memberPasswordSalt;
	private String memberName;
	private Date memberBirthdate;
	private Integer memberGender;
	private String memberPhone;
	private String memberAddress;
	private byte[] memberPhoto;
	private Timestamp memberCreatedAt;
	private Timestamp memberUpdatedAt;
	private Timestamp memberLastLoginTime;
	private Integer memberStatus;

	public MembersVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MembersVO(Integer memberId, String memberEmail, String memberPasswordHash, String memberPasswordSalt,
			String memberName, Date memberBirthdate, Integer memberGender, String memberPhone, String memberAddress,
			byte[] memberPhoto, Timestamp memberCreatedAt, Timestamp memberUpdatedAt, Timestamp memberLastLoginTime,
			Integer memberStatus) {
		super();
		this.memberId = memberId;
		this.memberEmail = memberEmail;
		this.memberPasswordHash = memberPasswordHash;
		this.memberPasswordSalt = memberPasswordSalt;
		this.memberName = memberName;
		this.memberBirthdate = memberBirthdate;
		this.memberGender = memberGender;
		this.memberPhone = memberPhone;
		this.memberAddress = memberAddress;
		this.memberPhoto = memberPhoto;
		this.memberCreatedAt = memberCreatedAt;
		this.memberUpdatedAt = memberUpdatedAt;
		this.memberLastLoginTime = memberLastLoginTime;
		this.memberStatus = memberStatus;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPasswordHash() {
		return memberPasswordHash;
	}

	public void setMemberPasswordHash(String memberPasswordHash) {
		this.memberPasswordHash = memberPasswordHash;
	}

	public String getMemberPasswordSalt() {
		return memberPasswordSalt;
	}

	public void setMemberPasswordSalt(String memberPasswordSalt) {
		this.memberPasswordSalt = memberPasswordSalt;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getMemberBirthdate() {
		return memberBirthdate;
	}

	public void setMemberBirthdate(Date memberBirthdate) {
		this.memberBirthdate = memberBirthdate;
	}

	public Integer getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(Integer memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public byte[] getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(byte[] memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public Timestamp getMemberCreatedAt() {
		return memberCreatedAt;
	}

	public void setMemberCreatedAt(Timestamp memberCreatedAt) {
		this.memberCreatedAt = memberCreatedAt;
	}

	public Timestamp getMemberUpdatedAt() {
		return memberUpdatedAt;
	}

	public void setMemberUpdatedAt(Timestamp memberUpdatedAt) {
		this.memberUpdatedAt = memberUpdatedAt;
	}

	public Timestamp getMemberLastLoginTime() {
		return memberLastLoginTime;
	}

	public void setMemberLastLoginTime(Timestamp memberLastLoginTime) {
		this.memberLastLoginTime = memberLastLoginTime;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}
}
