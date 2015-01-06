package ie.cit.tom.service.impl;

import java.util.List;
import java.util.ListIterator;

import ie.cit.tom.dao.AuthorityDao;
import ie.cit.tom.date.SetDates;
import ie.cit.tom.entity.Authority;
import ie.cit.tom.service.AuthorityService;

public class AuthorityServiceImpl implements AuthorityService {
	private AuthorityDao authorityDao;

	public SetDates setDates = new SetDates();
	
	public AuthorityServiceImpl(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
	@Override
	@Deprecated
	public void updateBook(Authority authority) {
		authorityDao.update(authority);
	}
	@Override
	@Deprecated
	public void addBook(Authority authority) {
		authorityDao.add(authority);	
	}
	@Override
	public void saveBook(Authority authority) {
		authorityDao.save(authority);	
	}
	@Override
	public Authority getByName(String username) {
		List<Authority> authorities = authorityDao.findAll();
		ListIterator<Authority> authorityList = authorities.listIterator();
		while(authorityList.hasNext()) {
			Authority a = authorityList.next();
			
			if(username.equals(a.getUsername())) {
				return authorityDao.getByName(a.getUsername());
			}
		}
		return null;
	}
}
