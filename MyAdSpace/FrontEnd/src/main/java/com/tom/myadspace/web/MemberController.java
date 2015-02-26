package com.tom.myadspace.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tom.myadspace.entity.Member;
import com.tom.myadspace.service.MemberService;

@Controller
public class MemberController {
	private MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	@RequestMapping("/member")
	public String memberList(Model model) {
		List<Member> members = memberService.findAll();
		model.addAttribute("members", members);
		return "memberList";		
	}
}
