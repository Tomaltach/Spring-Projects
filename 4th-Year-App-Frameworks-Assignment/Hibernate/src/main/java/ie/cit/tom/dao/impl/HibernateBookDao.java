package ie.cit.tom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.BookDao;
import ie.cit.tom.entity.Book;

@Repository
@Transactional
public class HibernateBookDao implements BookDao {	
	private SessionFactory sessionFactory;
	
	public HibernateBookDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Book getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Book) session.get(Book.class, id);
	}
	@Override
	public void add(Book b) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(b);
	}
	@Override
	public void delete(Book b) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(b);	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Book");
		return q.list();
	}
	@Override
	public void update(Book b) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(b);
	}
	@Override
	public void save(Book b) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(b);
	}
}