package ie.cit.tom.service;

import ie.cit.tom.entity.User;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public interface UserService {
	@Deprecated
	void updateUser(User user);	
	@Deprecated
	void addUser(User user);	
	void saveUser(User user);	
	void deleteUser(User user);
	User getById(int id);
	//ProfileForm getByName(String username);
	User getByName(String username);
	List<User> findAllUsers();	
}