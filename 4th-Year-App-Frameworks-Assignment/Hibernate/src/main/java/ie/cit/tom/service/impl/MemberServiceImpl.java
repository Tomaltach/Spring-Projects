package ie.cit.tom.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import ie.cit.tom.dao.AuthorityDao;
import ie.cit.tom.dao.LoanDao;
import ie.cit.tom.dao.MemberDao;
import ie.cit.tom.dao.UserDao;
import ie.cit.tom.date.CompareDates;
import ie.cit.tom.date.SetDates;
import ie.cit.tom.date.SetNewReturn;
import ie.cit.tom.entity.Authority;
import ie.cit.tom.entity.Loan;
import ie.cit.tom.entity.Member;
import ie.cit.tom.entity.User;
import ie.cit.tom.service.MemberService;

@Transactional
public class MemberServiceImpl implements MemberService {
	private MemberDao memberDao;
	private LoanDao loanDao;
	private UserDao userDao;
	private AuthorityDao authorityDao;

	public SetDates setDates = new SetDates();
	public CompareDates compareDates = new CompareDates();
	public SetNewReturn setReturn = new SetNewReturn();
	
	public MemberServiceImpl(MemberDao memberDao, LoanDao loanDao, UserDao userDao, AuthorityDao authorityDao) {
		this.memberDao = memberDao;
		this.loanDao = loanDao;
		this.userDao = userDao;
		this.authorityDao = authorityDao;
	}	
	@Override
	public void giveCorkPeopleANiceBonus(double bonusAmount) {
//		customerDao.updateBonusForCork("Cork", bonusAmount);
//		Bonus bonus = new Bonus();
//		bonus.setCity("Cork");
//		bonus.setAmount(bonusAmount);
//		bonusDao.add(bonus);
	}
	//@SuppressWarnings("deprecation")
	@Override
	public void updateMember(Member member) {
		//memberDao.update(member);
		memberDao.save(member);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void addMember(Member member) {
		memberDao.add(member);
	}	
	@SuppressWarnings("deprecation")
	@Override
	public void saveMember(Member member) {	
		User user = new User();
		String username = member.getName();
		username = username.toLowerCase();
		if(StringUtils.hasLength(username)) {
			username = username.replaceAll("\\s",".");		   
		}
		user.setUsername(username);
		user.setPassword("");
		user.setEnabled(true);
		userDao.add(user);
		
		Authority authority = new Authority();
		username = username.toLowerCase();
		authority.setUsername(username);
		authority.setAuthority("ROLE_USER");
		System.out.println("\n"+authority.getUsername()+"\n"+authority.getAuthority());
		authorityDao.add(authority);		
		
		memberDao.save(member);
	}
	@Override
	public void deleteMember(Member member) {
		User user = userDao.getById(member.getId());
		userDao.delete(user);
		memberDao.delete(member);
	}
	@Override
	public Member getMemberById(int id) {
		// get all members
		Member member = memberDao.getById(id);
		// get all loans
		List<Loan> loans = loanDao.findAll();
		ListIterator<Loan> loanList = loans.listIterator();
		// cycle through all loans
		while(loanList.hasNext()) {
			Loan l = loanList.next();
			// check is loan belongs to member
			if(l.getMember_id() == member.getId()) {
				BigDecimal bd = compareDates.getFine(l.getReturn_date());
				int compare = bd.compareTo(BigDecimal.ZERO);
				// check if loan is overdue
				if(compare > 0) {	
					//overdueLoans.add(loanDao.getById(l.getId()));
					bd.add(member.getBalance());
					member.setBalance(bd);
					memberDao.save(member);
					l.setFine(bd);
					loanDao.save(l);
				}
			}
		}
		return memberDao.getById(id);
	}
	@Override
	public List<Member> findAllMembers() {
		return memberDao.findAll();
	}
	@Override
	public boolean exists(int id) {
		return memberDao.getById(id) != null;
	}
	@Override
	public long daysBetween(Member m1, Member m2) {
/*
		long time1 = m1.getDateOfBirth().getTime();
		long time2 = m2.getDateOfBirth().getTime();
		return Math.abs((time1 - time2) 
                / (1000 * 60 * 60 * 24));
*/
		return (Long) null;
	}
	@Override
	public void payFine(Member member) {
		Member oldMember = memberDao.getById(member.getId());
		BigDecimal newFine = oldMember.getBalance().subtract(member.getBalance());
		if(newFine.compareTo(BigDecimal.ZERO) < 0) {
			newFine = BigDecimal.ZERO;
		}
		if(newFine.compareTo(BigDecimal.ZERO) == 0) {
			// get all loans
			List<Loan> loans = loanDao.findAll();
			ListIterator<Loan> loanList = loans.listIterator();
			
			// cycle through all loans
			while(loanList.hasNext()) {
				Loan l = loanList.next();
				// check is loan belongs to member
				if(l.getMember_id() == member.getId()) {
					BigDecimal bd = compareDates.getFine(l.getReturn_date());
					int compare = bd.compareTo(BigDecimal.ZERO);
					// check if loan is overdue
					if(compare > 0) {	
						l.setReturn_date(setDates.getReturnDate());
						l.setFine(new BigDecimal(0.00));
						loanDao.save(l);
					}
				}
			}
		} else {
			// get all loans
			List<Loan> loans = loanDao.findAll();
			ListIterator<Loan> loanList = loans.listIterator();
			
			// cycle through all loans
			while(loanList.hasNext()) {
				Loan l = loanList.next();
				// check is loan belongs to member
				if(l.getMember_id() == member.getId()) {
					BigDecimal bd = compareDates.getFine(l.getReturn_date());
					int compare = bd.compareTo(BigDecimal.ZERO);
					// check if loan is overdue
					if(compare > 0) {
						double d = bd.doubleValue();
						double newF = newFine.doubleValue();
						newF = Math.round(newF * 100) / 5;
						double divide = Math.round(d * 100.0) / 5;
						d = divide/newF;
						int days = (int) Math.round(d);
						
						System.out.println(days + "\n" + d);
						// set new date
						l.setReturn_date(setReturn.getReturnDate(days));
						l.setFine(new BigDecimal(0.00));
						loanDao.save(l);
						break;
					}
				}
			}
		}
		member.setBalance(newFine);
		memberDao.save(member);
	}
	@SuppressWarnings("unused")
	@Override
	public List<Member> findAllOverdueMembers() {
		// get all members
		List<Member> members = memberDao.findAll();
		List<Member> overdueMembers = new ArrayList<Member>();
		ListIterator<Member> memberList = members.listIterator();
		
		// get all loans
		List<Loan> loans = loanDao.findAll();
		List<Loan> overdueLoans = new ArrayList<Loan>();
		
		// cycle through all members
		while(memberList.hasNext()) {
			Member m = memberList.next();			
			ListIterator<Loan> loanList = loans.listIterator();
			// cycle through all loans
			while(loanList.hasNext()) {
				Loan l = loanList.next();
				// check is loan belongs to member
				if(l.getMember_id() == m.getId()) {
					BigDecimal bd = compareDates.getFine(l.getReturn_date());
					int compare = bd.compareTo(BigDecimal.ZERO);
					// check if loan is overdue
					if(compare > 0) {	
						//overdueLoans.add(loanDao.getById(l.getId()));
						bd.add(m.getBalance());
						m.setBalance(bd);
						memberDao.save(m);
						l.setFine(bd);
						loanDao.save(l);
						overdueMembers.add(memberDao.getById(m.getId()));
					}
				}
			}
		}
		return overdueMembers;
	}
}