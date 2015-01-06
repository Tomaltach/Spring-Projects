package ie.cit.tom.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.BookDao;
import ie.cit.tom.dao.LoanDao;
import ie.cit.tom.dao.MemberDao;
import ie.cit.tom.date.CompareDates;
import ie.cit.tom.date.SetDates;
import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Loan;
import ie.cit.tom.entity.Member;
import ie.cit.tom.service.LoanService;

@Transactional
public class LoanServiceImpl implements LoanService {
	private BookDao bookDao;
	private LoanDao loanDao;
	private MemberDao memberDao;	

	public SetDates setDates = new SetDates();
	public CompareDates compareDates = new CompareDates(); 

	public LoanServiceImpl(LoanDao loanDao, BookDao bookDao, MemberDao memberDao) {
		this.loanDao = loanDao;
		this.bookDao = bookDao;
		this.memberDao = memberDao;
	}
	@Override
	public void updateLoan(Loan loan) {
		loanDao.save(loan);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void addLoan(Loan loan) {
		loanDao.add(loan);
	}	
	@Override
	public void saveLoan(Loan loan) {
		// increment books allowed for memeber if alls good. do not loan book if less than 0
		Member member = memberDao.getById(loan.getMember_id());
		if(member.getBook_allowance() > 0) {
			member.setBook_allowance(member.getBook_allowance() - 1);
				
			// if borrowed less then 4 books loan can be taken out
			// checks for valid member id
			int member_id = loan.getMember_id();
			int book_id = loan.getBook_id();
			int validMember = 0;

			List<Member> memberList = memberDao.findAll();
			for(Member memberLoop : memberList) {
				int memberId = memberLoop.getId();
				if(memberId == member_id) {
					validMember = 1;
				}
			}
			
			// checks for valid book id
			List<Book> memberLoanBookList = bookDao.findAll();
			for(Book book : memberLoanBookList) {
				int bookid = book.getId();
				// if both memeber and book exist add loan
				if(bookid == book_id && validMember == 1) {
					book.setAvailable(false);
					Loan l = new Loan();
					
					l.setBook_id(loan.getBook_id());
					l.setMember_id(loan.getMember_id());
					l.setLoan_date(setDates.getLoanDate());
					l.setReturn_date(setDates.getReturnDate());
					l.setFine(new BigDecimal(0.00));					
					
					loanDao.save(l);
				}
			}
		}		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void deleteLoan(Loan loan) {
		loan = loanDao.getById(loan.getId());
		Book book = bookDao.getById(loan.getBook_id());
		
		Member member = memberDao.getById(loan.getMember_id());
		member.setBalance(compareDates.getFine(loan.getReturn_date()));
		memberDao.save(member);

		// increment books allowed for memeber if alls good
		Member memberDetails = memberDao.getById(loan.getMember_id());
		memberDetails.setBook_allowance(memberDetails.getBook_allowance() + 1);

		// set book to true meaning that the book is available again
		book.setAvailable(true);
		bookDao.update(book);
		
		loanDao.delete(loan);
	}
	@Override
	public Loan getById(int id) {
		Loan l = loanDao.getById(id);
		BigDecimal bd = compareDates.getFine(l.getReturn_date());
		int compare = bd.compareTo(BigDecimal.ZERO);
	
		if(compare > 0) {	
			l.setFine(bd);
			loanDao.save(l);
		}
		return loanDao.getById(id);
	}
	@Override
	public Loan getMemberById(int member_id) {
		// TODO Auto-generated method stub
		return loanDao.getByMemberId(member_id);
	}	
	@Override
	public Loan getBookById(int book_id) {
		// TODO Auto-generated method stub
		return loanDao.getByBookId(book_id);
	}
	@Override
	public List<Loan> findAllLoans() {
		return loanDao.findAll();
	}
	@Override
	public boolean exists(int id) {
		return loanDao.getById(id) != null;
	}
	@Override
	public long daysBetween(Loan l1, Loan l2) {
		long time1 = l1.getReturn_date().getTime();
		Date today = new Date();
		long time2 = today.getTime();
		return Math.abs((time1 - time2) 
                / (1000 * 60 * 60 * 24));
	}	
	@Override
	public void checkBook(Loan loan) {
		loan.setLoan_date(setDates.getLoanDate());
		loan.setReturn_date(setDates.getReturnDate());
		loan.setFine(new BigDecimal(0.00));
		loanDao.save(loan);
	}
	@Override
	public List<Loan> findAllOverdueLoans() {
		List<Loan> loans = loanDao.findAll();
		List<Loan> overdues = new ArrayList<Loan>();
		ListIterator<Loan> loanList = loans.listIterator();
		while(loanList.hasNext()) {
			Loan l = loanList.next();
			
			BigDecimal bd = compareDates.getFine(l.getReturn_date());
			int compare = bd.compareTo(BigDecimal.ZERO);
			
			if(compare > 0) {	
				l.setFine(bd);
				loanDao.save(l);
				overdues.add(loanDao.getById(l.getId()));
			}
		}
		return overdues;
	}
}
