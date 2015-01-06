package ie.cit.tom.service;

import java.util.List;

import ie.cit.tom.entity.Member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public interface MemberService {
	void giveCorkPeopleANiceBonus(double bonusAmount);	
	@Deprecated
	void updateMember(Member member);	
	@Deprecated
	void addMember(Member member);	
	void saveMember(Member member);	
	void deleteMember(Member member);	
	Member getMemberById(int id);	
	List<Member> findAllMembers();	
	boolean exists(int id);
	long daysBetween(Member m1, Member m2);
	void payFine(Member member);
	List<Member> findAllOverdueMembers();
}
