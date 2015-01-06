package ie.cit.tom.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.entity.Authority;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public interface AuthorityService {
	@Deprecated
	void updateBook(Authority authority);	
	@Deprecated
	void addBook(Authority authority);	
	void saveBook(Authority authority);
	Authority getByName(String username);
}
