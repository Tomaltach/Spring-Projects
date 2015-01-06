package ie.cit.tom.resource;

import java.util.List;

import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Member;
import ie.cit.tom.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/books")
public class BooksController {
	private BookService bookService;
	
	@Autowired
	public BooksController(BookService bookService) {
		this.bookService = bookService;
	}	
	@RequestMapping(value="/", method=RequestMethod.GET, headers="Content-Type=application/json")
	public @ResponseBody Book[] bookList() {
		List<Book> books = bookService.findAllBooks();
		Book[] custArray = new Book[books.size()];
		custArray = books.toArray(custArray);
		return custArray;
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Book getBook(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
		return book;
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Book bookAddFormHandler(@RequestBody Book book) {
		book.setAvailable(true);
		bookService.saveBook(book);
		return book;
	}
	@RequestMapping(value="/{id}/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateBook(@PathVariable("id") int id, @RequestBody Book book) {
		bookService.saveBook(book);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void loanBook(@PathVariable("id") int id, @RequestBody Book book) {
		Member member = new Member();
		bookService.loanBook(book, member);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable("id") int id) {
		Book book = new Book();
		book.setId(id);
		bookService.deleteBook(book);
	}
}
