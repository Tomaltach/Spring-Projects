package ie.cit.tom.form;

import java.util.ArrayList;
import java.util.List;

import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Query;

@SuppressWarnings("unused")
public class BookSearchForm {
	private Book book;
	private List<Book> books;
	private Query query;

	public BookSearchForm() {
		this.book = new Book();
		this.books = new ArrayList<Book>();
		this.query = new Query();
	}
	public BookSearchForm(Book book, Query query) {
		this.book = book;
		this.query = query;
	}
	public BookSearchForm(List<Book> books, Query query) {
		this.books = books;
		this.query = query;
	}
}
