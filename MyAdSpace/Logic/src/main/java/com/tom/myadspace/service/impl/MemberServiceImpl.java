package com.tom.myadspace.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tom.myadspace.dao.MemberDao;
import com.tom.myadspace.entity.Member;
import com.tom.myadspace.service.MemberService;

@Transactional
public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao;
	
	public MemberServiceImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	@Override
	public void addMember(Member member) {
		memberDao.addMember(member);
	}
	@Override
	public void updateMember(Member member) {
		memberDao.updateMember(member);
	}
	@Override
	public void deleteMember(Member member) {
		memberDao.deleteMember(member);
	}
	@Override
	public Member findById(int id) {
		return memberDao.findById(id);
	}
	@Override
	public Member findByUserName(String userName) {
		return memberDao.findByUserName(userName);
	}
	@Override
	public List<Member> findAll() {
		return memberDao.findAll();
	}
}
