package ie.cit.tom.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;

import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.BookDao;
import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Query;
import ie.cit.tom.service.QueryService;

@Transactional
public class QueryServiceImpl implements QueryService {
	private BookDao bookDao;
	
	public QueryServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	@Override
	public List<Book> getSearch(String search) {
		Query query = new Query();
		query.setSearch(search);
		return checkBookQuery(query);
	}
	@Override
	public List<Book> checkBookQuery(Query query) {
		List<Book> resultList = new ArrayList<Book>();
		resultList.addAll(getBookByIsbn(query.getSearch()));
		resultList.addAll(getBookByAuthor(query.getSearch()));
		resultList.addAll(getBookByTitle(query.getSearch()));
		printString(resultList);
		return removeDuplicates(resultList);
	}
	private boolean compareString(String search, String compare) {
		String s1 = search.toLowerCase();
		String s2 = compare.toLowerCase();
		if(s1.equals(s2)) {
			return true;
		}
		return false;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Book> removeDuplicates(List<Book> results) {
		HashSet hs = new HashSet();
		hs.addAll(results);
		results.clear();
		results.addAll(hs);
		return results;
	}
	private void printString(List<Book> results) {
		ListIterator<Book> bookList = results.listIterator();
		while(bookList.hasNext()) {
			Book b = bookList.next();
			System.out.println(
				"\nID:\t\t" + b.getId() +
				"\nTitle:\t\t" + b.getTitle() +
				"\nAuthor:\t\t" + b.getAuthor() +
				"\nPublisher:\t" + b.getPublisher() +
				"\nPub Date:\t" + b.getPublication_date() +
				"\nISBN:\t\t" + b.getIsbn() +
				"\nAvailable:\t" + b.isAvailable() +
				"\n"
			);
		}
	}
	@Override
	public List<Book> getBookByIsbn(String isbn) {
		List<Book> books = bookDao.findAll();
		List<Book> results = new ArrayList<Book>();
		ListIterator<Book> bookList = books.listIterator();
		while(bookList.hasNext()) {
			Book b = bookList.next();

			if(compareString(isbn, b.getIsbn())) {
				results.add(bookDao.getById(b.getId()));
			}
		}
		return results;
	}
	@Override
	public List<Book> getBookByAuthor(String author) {
		List<Book> books = bookDao.findAll();
		List<Book> results = new ArrayList<Book>();
		ListIterator<Book> bookList = books.listIterator();
		while(bookList.hasNext()) {
			Book b = bookList.next();
			
			if(compareString(author, b.getAuthor())) {	
				results.add(bookDao.getById(b.getId()));
			}
		}
		return results;
	}
	@Override
	public List<Book> getBookByTitle(String title) {
		List<Book> books = bookDao.findAll();
		List<Book> results = new ArrayList<Book>();
		ListIterator<Book> bookList = books.listIterator();
		while(bookList.hasNext()) {
			Book b = bookList.next();
			
			if(compareString(title, b.getTitle())) {	
				results.add(bookDao.getById(b.getId()));
			}
		}
		return results;
	}	
}
