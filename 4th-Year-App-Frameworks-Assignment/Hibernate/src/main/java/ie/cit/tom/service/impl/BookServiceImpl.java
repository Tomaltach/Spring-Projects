package ie.cit.tom.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.BookDao;
import ie.cit.tom.dao.LoanDao;
import ie.cit.tom.dao.MemberDao;
import ie.cit.tom.date.SetDates;
import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Loan;
import ie.cit.tom.entity.Member;
import ie.cit.tom.service.BookService;

@Transactional
public class BookServiceImpl implements BookService {	
	private MemberDao memberDao;
	private BookDao bookDao;
	private LoanDao loanDao;

	public SetDates setDates = new SetDates();
	
	public BookServiceImpl(MemberDao memberDao, BookDao bookDao, LoanDao loanDao) {
		this.memberDao = memberDao;
		this.bookDao = bookDao;
		this.loanDao = loanDao;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void updateBook(Book book) {
		bookDao.update(book);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void addBook(Book book) {
		bookDao.add(book);
	}	
	@Override
	public void saveBook(Book book) {
		bookDao.save(book);
	}
	@Override
	public void deleteBook(Book book) {
		bookDao.delete(book);
	}
	@Override
	public Book getBookById(int id) {
		// TODO Auto-generated method stub
		return bookDao.getById(id);
	}
	@Override
	public List<Book> findAllBooks() {
		return bookDao.findAll();
	}
	@Override
	public boolean exists(int id) {
		return bookDao.getById(id) != null;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void loanBook(Book book, Member memberIn) {
		// update book
		bookDao.save(book);
		
		Member member = memberDao.getById(1);//memberIn.getId());
		
		// update member
		member.setBook_allowance(member.getBook_allowance() - 1);
		memberDao.save(member);
		
		// new loan
		Loan loan = new Loan();
		loan.setMember_id(member.getId());
		loan.setBook_id(book.getId());		
		loan.setLoan_date(setDates.getLoanDate());
		loan.setReturn_date(setDates.getReturnDate());
		loan.setFine(new BigDecimal(0.00));
		loanDao.add(loan);
	}
}