package ie.cit.tom.resource;

import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Query;
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
@RequestMapping("/searches")
public class QueriesController {
	private BookService bookService;
	
	@Autowired
	public QueriesController(BookService bookService) {
		this.bookService = bookService;
	}	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public @ResponseBody Query getQuery(@PathVariable("search") String search) {
		Query query = new Query();
		return query;
	}
	@RequestMapping(value="/search", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void findQuery(@RequestBody Query query) {
		//queryService.checkBookQuery(query);
	}	
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public @ResponseBody Book getBook(@PathVariable("id") int id) {
		Book book = bookService.getBookById(id);
		return book;
	}
}
