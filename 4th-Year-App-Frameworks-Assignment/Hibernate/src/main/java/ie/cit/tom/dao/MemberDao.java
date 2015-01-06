package ie.cit.tom.dao;

import ie.cit.tom.entity.Member;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
	Member getById(int id);	
	@Deprecated
	void update(Member m);	
	@Deprecated
	void add(Member m);	
	void save(Member m);	
	void delete(Member m);	
	List<Member> findAll();
}
