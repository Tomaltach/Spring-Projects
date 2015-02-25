package com.tom.myadspace.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tom.myadspace.entity.Member;

@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=SQLException.class)
public interface MemberService {
	void addMember(Member member);
	void updateMember(Member member);
	void deleteMember(Member member);
	Member findById(int id);
	Member findByUserName(String userName);
	List<Member> findAll();
}
