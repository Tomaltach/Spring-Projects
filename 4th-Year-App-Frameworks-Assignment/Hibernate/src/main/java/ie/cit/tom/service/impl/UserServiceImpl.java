package ie.cit.tom.service.impl;

import java.util.List;
import java.util.ListIterator;

import org.springframework.transaction.annotation.Transactional;

import ie.cit.tom.dao.MemberDao;
import ie.cit.tom.dao.UserDao;
import ie.cit.tom.entity.User;
import ie.cit.tom.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	@SuppressWarnings("unused")
	private MemberDao memberDao;
	
	public UserServiceImpl(UserDao userDao, MemberDao memberDao) {
		this.userDao = userDao;
		this.memberDao = memberDao;
	}
	@SuppressWarnings("deprecation")
	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void addUser(User user) {
		userDao.add(user);
	}
	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}
	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}
	@Override
	public User getById(int id) {
		return userDao.getById(id);
	}
	@Override
	public User getByName(String username) {
		List<User> users = userDao.findAll();
		ListIterator<User> userList = users.listIterator();
		while(userList.hasNext()) {
			User u = userList.next();
			
			if(username.equals(u.getUsername())) {
				return userDao.getById(u.getId());
			}
		}
		return null;
	}
	@Override
	public List<User> findAllUsers() {
		return userDao.findAll();
	}
}

/*
@Override
public ProfileForm getByName(String username) {
	List<User> users = userDao.findAll();
	ListIterator<User> userList = users.listIterator();
	while(userList.hasNext()) {
		User u = userList.next();
		
		if(username.equals(u.getUsername())) {
			User user = userDao.getById(u.getId());
			Member member = memberDao.getById(u.getId());
			return new ProfileForm(user, member);
		}
	}
	return null;
}
*/