package ie.cit.tom.service;

import ie.cit.tom.entity.Loan;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public interface LoanService {
	@Deprecated
	void updateLoan(Loan loan);	
	@Deprecated
	void addLoan(Loan loan);	
	void saveLoan(Loan loan);	
	void deleteLoan(Loan loan);
	Loan getById(int id);
	Loan getMemberById(int id);
	Loan getBookById(int id);	
	List<Loan> findAllLoans();	
	boolean exists(int id);
	long daysBetween(Loan l1, Loan l2);
	void checkBook(Loan loan);
	List<Loan> findAllOverdueLoans();
}
