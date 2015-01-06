package ie.cit.tom.dao;

import ie.cit.tom.entity.Loan;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface LoanDao {	
	Loan getById(int id);
	Loan getByMemberId(int member_id);	
	Loan getByBookId(int book_id);
	@Deprecated
	void update(Loan l);	
	@Deprecated
	void add(Loan l);	
	void save(Loan l);	
	void delete(Loan l);	
	List<Loan> findAll();
}