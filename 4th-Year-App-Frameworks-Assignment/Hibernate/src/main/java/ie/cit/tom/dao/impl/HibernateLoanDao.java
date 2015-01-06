package ie.cit.tom.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.LoanDao;
import ie.cit.tom.entity.Loan;

@Repository
@Transactional
public class HibernateLoanDao implements LoanDao {
	private SessionFactory sessionFactory;
	
	public HibernateLoanDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Loan getById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Loan) session.get(Loan.class, id);
	}
	@Override
	public Loan getByMemberId(int member_id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Loan) session.get(Loan.class, member_id);
	}
	@Override
	public Loan getByBookId(int book_id) {
		Session session = this.sessionFactory.getCurrentSession();
		return (Loan) session.get(Loan.class, book_id);
	}
	@Override
	public void add(Loan l) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(l);
	}
	@Override
	public void delete(Loan l) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(l);	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Loan> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("from Loan");
		return q.list();
	}
	@Override
	public void update(Loan l) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(l);
	}
	@Override
	public void save(Loan l) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(l);
	}
}