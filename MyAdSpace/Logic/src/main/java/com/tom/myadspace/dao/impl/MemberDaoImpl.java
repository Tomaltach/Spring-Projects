package com.tom.myadspace.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tom.myadspace.dao.MemberDao;
import com.tom.myadspace.entity.Member;

@Repository
@Transactional
public class MemberDaoImpl implements MemberDao {
	private SessionFactory sessionFactory;
	
	public MemberDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void addMember(Member member) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(member);
	}
	@Override
	public void updateMember(Member member) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(member);
	}
	@Override
	public void deleteMember(Member member) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(member);
	}
	@Override
	public Member findById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Member) session.get(Member.class, id);
	}
	@Override
	public Member findByUserName(String userName) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Member) session.get(Member.class, userName);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM member");
		return query.list();
	}
}
