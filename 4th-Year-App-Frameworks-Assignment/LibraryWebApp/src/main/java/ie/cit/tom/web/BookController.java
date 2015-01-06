package ie.cit.tom.web;

import ie.cit.tom.entity.Book;
import ie.cit.tom.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class BookController {
	private BookService bookService;
	private final String REST_URL = "http://localhost:8080/LibraryWebApp";
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }	
	@RequestMapping("/book")
	public String BookList(Model model) {
		//Customer[] customers = new RestTemplate().
		//		getForObject(REST_URL + "/customers/", Customer[].class);
		List<Book> books = bookService.findAllBooks();
		model.addAttribute("books", books);
		return "bookList";
	}
	@RequestMapping(value="/book/{id}", method=RequestMethod.GET)
	public String showBook(@PathVariable("id") int id, Model model) {
		Book book = new RestTemplate().getForObject(REST_URL + "/books/{id}", Book.class, id);
		model.addAttribute("book", book);

		return "bookDetails";
	}
	@RequestMapping(value="/book/{id}", method=RequestMethod.POST)
	public String loanBook(@PathVariable("id") int id, Model model, @ModelAttribute @Valid Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "bookDetails";
		}
		new RestTemplate().put(REST_URL + "/books/{id}", book, book.getId());
		model.addAttribute("book", book);
		return "redirect:/book";
	}
	@RequestMapping(value="/book/{id}/update", method=RequestMethod.GET)
	public String bookUpdate(@PathVariable("id") int id, Model model) {
		Book book = new RestTemplate().getForObject(REST_URL + "/books/{id}", Book.class, id);
		model.addAttribute("book", book);
		return "bookForm";
	}
	@RequestMapping(value="/book/{id}/update", method=RequestMethod.POST)
	public String updateBook(@PathVariable("id") int id, Model model, @ModelAttribute @Valid Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "bookForm";
		}
		new RestTemplate().put(REST_URL + "/books/{id}/update", book, book.getId());
		model.addAttribute("book", book);
		return "redirect:/book";
	}
	@RequestMapping("/book/{id}/delete")
	public String bookDelete(@PathVariable("id") int id, Model model) {
		new RestTemplate().delete(REST_URL + "/books/{id}", id);
		model.addAttribute("message", "Book deleted successfully");
		return "redirect:/book";
	}	
	@RequestMapping(value="/book/add", method=RequestMethod.GET)
	public String bookAdd(Model model) {
		model.addAttribute(new Book());
		return "bookForm";
	}	
	@SuppressWarnings("unused")
	@RequestMapping(value="/book/add", method=RequestMethod.POST)
	public String addBook(Model model, @ModelAttribute @Valid Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "bookForm";
		}		
		Book b = new RestTemplate().postForObject(REST_URL + "/books/", book, Book.class);
		return "redirect:/book";
	}
}