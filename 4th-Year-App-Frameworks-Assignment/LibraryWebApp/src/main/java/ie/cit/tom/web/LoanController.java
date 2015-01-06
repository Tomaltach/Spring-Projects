package ie.cit.tom.web;

import ie.cit.tom.entity.Loan;
import ie.cit.tom.service.LoanService;

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
public class LoanController {
	private LoanService loanService;
	private final String REST_URL = "http://localhost:8080/LibraryWebApp";
	
	@Autowired
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }
	@RequestMapping("/loan")
	public String loanList(Model model) {
		//Customer[] customers = new RestTemplate().
		//		getForObject(REST_URL + "/customers/", Customer[].class);
		List<Loan> loans = loanService.findAllLoans();
		model.addAttribute("loans", loans);
		return "loanList";
	}
	@RequestMapping("/loan/overdue")
	public String overdueLoanList(Model model) {
		//Customer[] customers = new RestTemplate().
		//		getForObject(REST_URL + "/customers/", Customer[].class);
		List<Loan> loans = loanService.findAllOverdueLoans();
		model.addAttribute("loans", loans);
		return "loanOverdue";
	}
	@RequestMapping(value="/loan/{id}", method=RequestMethod.GET)
	public String showLoan(@PathVariable("id") int id, Model model) {
		Loan loan = new RestTemplate().getForObject(REST_URL + "/loans/{id}", Loan.class, id);
		model.addAttribute("loan", loan);

		return "loanDetails";
	}
	@RequestMapping(value="/loan/{id}/update", method=RequestMethod.GET)
	public String loanUpdate(@PathVariable("id") int id, Model model) {
		Loan loan = new RestTemplate().getForObject(REST_URL + "/loans/{id}", Loan.class, id);
		model.addAttribute("loan", loan);
		return "loanUpdate";
	}	
	@RequestMapping(value="/loan/{id}/update", method=RequestMethod.POST)
	public String updateLoan(@PathVariable("id") int id, Model model, @ModelAttribute @Valid Loan loan, BindingResult result) {
		if(result.hasErrors()) {
			return "loanUpdate";
		}
		new RestTemplate().put(REST_URL + "/loans/{id}/update", loan, loan.getId());		
		model.addAttribute("loan", loan);
		return "redirect:/loan";
	}	
	@RequestMapping("/loan/{id}/delete")
	public String loanDelete(@PathVariable("id") int id, Model model) {
		new RestTemplate().delete(REST_URL + "/loans/{id}", id);
		model.addAttribute("message", "Loan deleted successfully");
		return "redirect:/loan";
	}	
	@RequestMapping(value="/loan/add", method=RequestMethod.GET)
	public String loanAdd(Model model) {
		model.addAttribute(new Loan());
		return "loanForm";
	}	
	@SuppressWarnings("unused")
	@RequestMapping(value="/loan/add", method=RequestMethod.POST)
	public String addLoan(Model model, @ModelAttribute @Valid Loan loan, BindingResult result) {
		if(result.hasErrors()) {
			return "loanForm";
		}	
		Loan l = new RestTemplate().postForObject(REST_URL + "/loans/", loan, Loan.class);
		return "redirect:/loan";
	}
}