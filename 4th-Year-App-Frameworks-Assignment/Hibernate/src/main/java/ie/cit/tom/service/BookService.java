package ie.cit.tom.service;

import java.util.List;

import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public interface BookService {
	@Deprecated
	void updateBook(Book book);	
	@Deprecated
	void addBook(Book book);	
	void saveBook(Book book);	
	void deleteBook(Book book);	
	Book getBookById(int id);	
	List<Book> findAllBooks();	
	boolean exists(int id);
	void loanBook(Book book, Member member);
}
