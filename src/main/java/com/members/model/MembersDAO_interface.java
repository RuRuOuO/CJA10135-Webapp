package com.members.model;

import java.util.*;

public interface MembersDAO_interface {
	public void insert(MembersVO membersVO);

	public void update(MembersVO membersVO);

	public void delete(Integer memberId);

	public MembersVO findByPrimaryKey(Integer memberId);

	public List<MembersVO> getAll();
}
