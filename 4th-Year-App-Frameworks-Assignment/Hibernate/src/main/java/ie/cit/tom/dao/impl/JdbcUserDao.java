package ie.cit.tom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import ie.cit.tom.dao.UserDao;
import ie.cit.tom.dao.mapper.UserMapper;
import ie.cit.tom.entity.User;

public class JdbcUserDao implements UserDao {
	private JdbcTemplate jdbcTemplate;

	public JdbcUserDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public User getById(int id) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM users WHERE id = ?",
					new UserMapper(),
					id);
		}
		catch (EmptyResultDataAccessException e) {
				return null;
		}
	}
	@Override
	public User getByName(String username) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM users WHERE username = ?",
					new UserMapper(),
					username);
		}
		catch (EmptyResultDataAccessException e) {
				return null;
		}
	}
	@Override
	@Deprecated
	public void update(User u) {
		// TODO Auto-generated method stub
		
	}
	@Override
	@Deprecated
	public void add(final User u) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO users(" +
									"username, " +
									"password, " +
									"enabled" +
							") " +
							"VALUES (?,?,?)"
						);
						ps.setString(1, u.getUsername());
						ps.setString(2, u.getPassword());
						ps.setBoolean(3, u.isEnabled());
						
						return ps;
					}
				});
	}
	@Override
	public void save(User u) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(User u) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
