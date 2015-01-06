package ie.cit.tom.resource;

import ie.cit.tom.entity.Loan;
import ie.cit.tom.service.LoanService;

import java.util.List;

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
@RequestMapping("/loans")
public class LoansController {
	private LoanService loanService;
	
	@Autowired
	public LoansController(LoanService loanService) {
		this.loanService = loanService;
	}	
	@RequestMapping(value="/", method=RequestMethod.GET, headers="Content-Type=application/json")
	public @ResponseBody Loan[] loanList() {
		List<Loan> loan = loanService.findAllLoans();
		Loan[] custArray = new Loan[loan.size()];
		custArray = loan.toArray(custArray);
		return custArray;
	}
	@RequestMapping(value="/overdue", method=RequestMethod.GET, headers="Content-Type=application/json")
	public @ResponseBody Loan[] overdueLoanList() {
		List<Loan> loan = loanService.findAllOverdueLoans();
		Loan[] custArray = new Loan[loan.size()];
		custArray = loan.toArray(custArray);
		return custArray;
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Loan getLoan(@PathVariable("id") int id) {
		Loan loan = loanService.getById(id);
		return loan;
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Loan loanAddFormHandler(@RequestBody Loan loan) {
		//loanService.checkBook(loan);
		loanService.saveLoan(loan);
		return loan;
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/{id}/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateLoan(@PathVariable("id") int id, @RequestBody Loan loan) {
		loanService.updateLoan(loan);
	}	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLoan(@PathVariable("id") int id) {
		Loan loan = new Loan();
		loan.setId(id);
		loanService.deleteLoan(loan);
	}
}
