package ie.cit.tom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.UserDao;
import ie.cit.tom.entity.User;

@Repository
@Transactional
public class HibernateUserDao implements UserDao{
	private SessionFactory sessionFactory;
	
	public HibernateUserDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public User getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (User) session.get(User.class, id);
	}
	@Override
	public User getByName(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		return (User) session.get(User.class, username);
	}
	@Override
	public void add(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(u);
	}
	@Override
	public void delete(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(u);	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from User");
		return q.list();
	}
	@Override
	public void update(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(u);
	}
	@Override
	public void save(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(u);
	}
}