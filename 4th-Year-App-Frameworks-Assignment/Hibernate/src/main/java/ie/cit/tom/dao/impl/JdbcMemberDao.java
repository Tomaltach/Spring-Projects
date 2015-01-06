package ie.cit.tom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import ie.cit.tom.dao.MemberDao;
import ie.cit.tom.dao.mapper.MemberMapper;
import ie.cit.tom.entity.Member;

public class JdbcMemberDao implements MemberDao {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcMemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Member getById(int id) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM member WHERE id = ?",
					new MemberMapper(),
					id);
		}
		catch (EmptyResultDataAccessException e) {
				return null;
		}
	}
	@Override
	public void add(final Member member) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO member(" +
									"name, " +
									"address1, " +
									"address2, " +
									"town, " +
									"contact_number, " +
									"book_allowance, " +
									"balance" +
									"active" +
							") " +
							"VALUES (?,?,?,?,?,?,?,?)"
						);
						ps.setString(1, member.getName());
						ps.setString(2, member.getAddress1());
						ps.setString(3, member.getAddress2());
						ps.setString(4, member.getTown());
						ps.setString(5, member.getContact_number());
						ps.setInt(6, member.getBook_allowance());
						ps.setBigDecimal(7, member.getBalance());
						ps.setBoolean(8, member.isActive());
						
						return ps;
					}
				});
	}
	@Override
	public void delete(Member id) {
		// TODO Auto-generated method stub
	}
	@Override
	public List<Member> findAll() {
		return jdbcTemplate.query("SELECT * FROM member ORDER by name", new MemberMapper());
	}
	@Override
	public void update(Member m) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void save(Member m) {
		// TODO Auto-generated method stub	
	}
}
