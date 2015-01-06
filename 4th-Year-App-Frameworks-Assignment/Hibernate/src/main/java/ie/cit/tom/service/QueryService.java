package ie.cit.tom.service;

import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Query;

import java.util.List;

public interface QueryService {
	List<Book> getSearch(String search);
	List<Book> checkBookQuery(Query query);
	List<Book> getBookByIsbn(String isbn);
	List<Book> getBookByAuthor(String author);
	List<Book> getBookByTitle(String title);
}
