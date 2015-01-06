package ie.cit.tom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.AuthorityDao;
import ie.cit.tom.entity.Authority;

@Repository
@Transactional
public class HibernateAuthorityDao implements AuthorityDao {
	private SessionFactory sessionFactory;
	
	public HibernateAuthorityDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	@Override
	@Deprecated
	public void update(Authority a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(a);
	}
	@Override
	@Deprecated
	public void add(Authority a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(a);	
	}
	@Override
	public void save(Authority a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(a);	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Authority> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Authority");
		return q.list();
	}
	@Override
	public Authority getByName(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Authority) session.get(Authority.class, username);
	}
}
