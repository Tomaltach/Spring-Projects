package ie.cit.tom.dao;

import java.util.List;

import ie.cit.tom.entity.Authority;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityDao {	
	@Deprecated
	void update(Authority a);	
	@Deprecated
	void add(Authority a);	
	void save(Authority a);		
	List<Authority> findAll();
	Authority getByName(String username);
}
