package ie.cit.tom.web;

import ie.cit.tom.entity.Member;
import ie.cit.tom.service.MemberService;

import java.util.List;

import javax.validation.Valid;

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
public class MemberController {
	private MemberService memberService;
	private final String REST_URL = "http://localhost:8080/LibraryWebApp";
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@RequestMapping("/member")
	public String MemberList(Model model) {
		//Customer[] customers = new RestTemplate().
		//		getForObject(REST_URL + "/customers/", Customer[].class);
		List<Member> members = memberService.findAllMembers();
		model.addAttribute("members", members);
		return "memberList";
	}
	@RequestMapping("/member/overdue")
	public String MemberOverdueList(Model model) {
		//Customer[] customers = new RestTemplate().
		//		getForObject(REST_URL + "/customers/", Customer[].class);
		List<Member> members = memberService.findAllOverdueMembers();
		model.addAttribute("members", members);
		return "memberOverdue";
	}
	@RequestMapping(value="/member/{id}", method=RequestMethod.GET)
	public String showMember(@PathVariable("id") int id, Model model) {
		Member member = new RestTemplate().getForObject(REST_URL + "/members/{id}", Member.class, id);
		model.addAttribute("member", member);
		//if(member.isLate()) {
		//	model.addAttribute("msg", new String("Whoa, this dude is late bru!"));
		//}
		return "memberDetails";
	}
	@RequestMapping(value="/member/{id}", method=RequestMethod.POST)
	public String payFine(@PathVariable("id") int id, Model model, @ModelAttribute @Valid Member member, BindingResult result) {
		if(result.hasErrors()) {
			return "memberDetails";
		}
		new RestTemplate().put(REST_URL + "/members/{id}", member, member.getId());
		model.addAttribute("member", member);
		return "redirect:/member";
	}	
	@RequestMapping(value="/member/{id}/update", method=RequestMethod.GET)
	public String memberUpdate(@PathVariable("id") int id, Model model) {
		Member member = new RestTemplate().getForObject(REST_URL + "/members/{id}", Member.class, id);
		model.addAttribute("member", member);
		return "memberForm";
	}
	@RequestMapping(value="/member/{id}/update", method=RequestMethod.POST)
	public String updateMember(@PathVariable("id") int id, Model model, @ModelAttribute @Valid Member member, BindingResult result) {
		if(result.hasErrors()) {
			return "memberForm";
		}
		new RestTemplate().put(REST_URL + "/members/{id}/update", member, member.getId());
		model.addAttribute("member", member);
		return "redirect:/member";
	}	
	@RequestMapping("/member/{id}/delete")
	public String memberDelete(@PathVariable("id") int id, Model model) {
		new RestTemplate().delete(REST_URL + "/members/{id}", id);
		model.addAttribute("message", "Member deleted successfully");
		return "redirect:/member";
	}	
	@RequestMapping(value="/member/add", method=RequestMethod.GET)
	public String memberAdd(Model model) {
		model.addAttribute(new Member());
		return "memberForm";
	}	
	@SuppressWarnings("unused")
	@RequestMapping(value="/member/add", method=RequestMethod.POST)
	public String addMember(Model model, @ModelAttribute @Valid Member member) {
		Member m = new RestTemplate().postForObject(REST_URL + "/members/", member, Member.class);
		return "redirect:/member";
	}
}