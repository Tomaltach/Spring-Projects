package ie.cit.tom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.MemberDao;
import ie.cit.tom.entity.Member;

@Repository
@Transactional
public class HibernateMemberDao implements MemberDao {
	private SessionFactory sessionFactory;
	
	public HibernateMemberDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Member getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Member) session.get(Member.class, id);
	}
	@Override
	public void add(Member m) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(m);
	}
	@Override
	public void delete(Member m) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(m);	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Member");
		return q.list();
	}
	@Override
	public void update(Member m) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(m);
	}
	@Override
	public void save(Member m) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(m);
	}
}