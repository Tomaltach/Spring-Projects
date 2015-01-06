package ie.cit.tom.dao;

import ie.cit.tom.entity.Book;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BookDao {
	Book getById(int id);	
	@Deprecated
	void update(Book b);	
	@Deprecated
	void add(Book b);	
	void save(Book b);	
	void delete(Book b);	
	List<Book> findAll();
}
