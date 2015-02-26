package com.tom.myadspace.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tom.myadspace.entity.Member;
import com.tom.myadspace.service.MemberService;

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
		List<Member> members = memberService.findAll();
		Member[] memberArray = new Member[members.size()];
		memberArray = members.toArray(memberArray);
		return memberArray;
	}
}
