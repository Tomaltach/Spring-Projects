package ie.cit.tom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import ie.cit.tom.dao.AuthorityDao;
import ie.cit.tom.dao.mapper.AuthorityMapper;
import ie.cit.tom.entity.Authority;

public class JdbcAuthorityDao implements AuthorityDao {
	private JdbcTemplate jdbcTemplate;

	public JdbcAuthorityDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	@Deprecated
	public void update(Authority a) {
		// TODO Auto-generated method stub
	}
	@Override
	@Deprecated
	public void add(final Authority a) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO authorities(" +
									"username, " +
									"authority" +
							") " +
							"VALUES (?,?)"
						);
						ps.setString(1, a.getUsername());
						ps.setString(2, a.getAuthority());
						
						return ps;
					}
				});
	}
	@Override
	public void save(Authority a) {
		// TODO Auto-generated method stub	
	}
	@Override
	public List<Authority> findAll() {
		return jdbcTemplate.query("SELECT * FROM authorities", new AuthorityMapper());
	}
	@Override
	public Authority getByName(String username) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM authorities WHERE username = ?",
					new AuthorityMapper(),
					username);
		}
		catch (EmptyResultDataAccessException e) {
				return null;
		}
	}
}
