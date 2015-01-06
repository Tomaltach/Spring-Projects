package ie.cit.tom.resource;

import java.util.List;

import ie.cit.tom.entity.Member;
import ie.cit.tom.service.MemberService;

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
@RequestMapping("/members")
public class MembersController {
	private MemberService memberService;
	
	@Autowired
	public MembersController(MemberService memberService) {
		this.memberService = memberService;
	}	
	@RequestMapping(value="/", method=RequestMethod.GET, headers="Content-Type=application/json")
	public @ResponseBody Member[] memberList() {
		List<Member> members = memberService.findAllMembers();
		Member[] custArray = new Member[members.size()];
		custArray = members.toArray(custArray);
		return custArray;
	}
	@RequestMapping(value="/overdue", method=RequestMethod.GET, headers="Content-Type=application/json")
	public @ResponseBody Member[] memberOverdueList() {
		List<Member> members = memberService.findAllOverdueMembers();
		Member[] custArray = new Member[members.size()];
		custArray = members.toArray(custArray);
		return custArray;
	}
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public @ResponseBody Member getMember(@PathVariable("id") int id) {
		Member member = memberService.getMemberById(id);
		return member;
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public @ResponseBody void payFine(@PathVariable("id") int id, @RequestBody Member member) {
		memberService.payFine(member);
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Member memberAddFormHandler(@RequestBody Member member) {
		memberService.saveMember(member);
		return member;
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/{id}/update", method=RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateMember(@PathVariable("id") int id, @RequestBody Member member) {
		memberService.updateMember(member);
	}	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMember(@PathVariable("id") int id) {
		Member member = new Member();
		member.setId(id);
		memberService.deleteMember(member);
	}
}
