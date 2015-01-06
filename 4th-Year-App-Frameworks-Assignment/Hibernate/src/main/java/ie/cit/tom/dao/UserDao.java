package ie.cit.tom.dao;

import ie.cit.tom.entity.User;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
	User getById(int id);
	User getByName(String username);
	@Deprecated
	void update(User u);	
	@Deprecated
	void add(User u);	
	void save(User u);	
	void delete(User u);	
	List<User> findAll();
}
