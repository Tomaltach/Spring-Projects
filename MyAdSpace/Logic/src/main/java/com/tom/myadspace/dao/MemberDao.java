package com.tom.myadspace.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tom.myadspace.entity.Member;

@Repository
public interface MemberDao {
	void addMember(Member member);
	void updateMember(Member member);
	void deleteMember(Member member);
	Member findById(int id);
	Member findByUserName(String userName);
	List<Member> findAll();
}
