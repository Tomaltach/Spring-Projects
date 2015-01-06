package ie.cit.tom.dao.mapper;

import ie.cit.tom.entity.Authority;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AuthorityMapper implements RowMapper<Authority> {
	public Authority mapRow(ResultSet rs, int i) throws SQLException {
		Authority a = new Authority();
		a.setUsername(rs.getString("username"));
		a.setAuthority(rs.getString("authority"));
		return a;
	}
}
