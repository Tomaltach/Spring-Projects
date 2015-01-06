package ie.cit.tom.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.tom.entity.Member;

public class MemberMapper implements RowMapper<Member> {
	public Member mapRow(ResultSet rs, int i) throws SQLException {
		Member m = new Member();
		m.setId(rs.getInt("id"));
		m.setName(rs.getString("name"));
		m.setAddress1(rs.getString("address1"));
		m.setAddress2(rs.getString("address2"));
		m.setTown(rs.getString("town"));
		m.setContact_number(rs.getString("contact_number"));
		m.setBook_allowance(rs.getInt("book_allowance"));
		m.setBalance(rs.getBigDecimal("balance"));
		m.setActive(rs.getBoolean("active"));
		return m;
	}
}
