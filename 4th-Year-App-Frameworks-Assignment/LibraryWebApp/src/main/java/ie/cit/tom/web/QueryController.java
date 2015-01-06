package ie.cit.tom.web;

import java.util.List;

import javax.validation.Valid;

import ie.cit.tom.entity.Book;
import ie.cit.tom.entity.Query;
import ie.cit.tom.service.QueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class QueryController {
	private QueryService queryService;
	private final String REST_URL = "http://localhost:8080/LibraryWebApp";

	private String tempSearch = "";
	
	@Autowired
	public QueryController(QueryService queryService) {
		this.queryService = queryService;
	}
	@RequestMapping("/search")
	public String getSearch(Model model) {
		Query query = new Query();
		model.addAttribute("query", query);
		return "querySearch";
	}	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String searchQuery(Model model, @ModelAttribute @Valid Query query, BindingResult result) {
		query.setSearch(query.getSearch().substring(1));
		tempSearch = query.getSearch();
		if(result.hasErrors()) {
			return "querySearch";
		}
		new RestTemplate().put(REST_URL + "/searches/search", query, query.getSearch());		
		model.addAttribute("query", query);
		return "redirect:/search/results";
	}
	@RequestMapping("/search/results")
	public String searchResults(Model model) {
		Query query = new Query();
		query.setSearch(tempSearch);
		List<Book> books = queryService.checkBookQuery(query);
		//model.addAttribute("bookSearchForm", new BookSearchForm(books, query));
		model.addAttribute("books", books);
		return "queryResults";
	}
	@RequestMapping(value="/search/book/{id}", method=RequestMethod.GET)
	public String getBook(@PathVariable("id") int id, Model model) {
		Book book = new RestTemplate().getForObject(REST_URL + "/searches/book/{id}", Book.class, id);
		model.addAttribute("book", book);

		return "bookDetails";
	}
	@RequestMapping(value="/search/book/{id}", method=RequestMethod.POST)
	public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute @Valid Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "bookDetails";
		}
		new RestTemplate().put(REST_URL + "/books/{id}", book, book.getId());
		model.addAttribute("book", book);
		return "redirect:/book";
	}
}
