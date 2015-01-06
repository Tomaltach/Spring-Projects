package ie.cit.tom.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import ie.cit.tom.dao.LoanDao;
import ie.cit.tom.dao.mapper.LoanMapper;
import ie.cit.tom.entity.Loan;

public class JdbcLoanDao implements LoanDao {
	private JdbcTemplate jdbcTemplate;

	public JdbcLoanDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Loan getById(int id) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM loan WHERE id = ?",
					new LoanMapper(),
					id);
		}
		catch (EmptyResultDataAccessException e) {
				return null;
		}
	}
	public Loan getByMemberId(int member_id) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM loan WHERE member_id = ?",
					new LoanMapper(),
					member_id);
		}
		catch (EmptyResultDataAccessException e) {
				return null;
		}
	}
	@Override
	public Loan getByBookId(int book_id) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM loan WHERE book_id = ?",
					new LoanMapper(),
					book_id);
		}
		catch (EmptyResultDataAccessException e) {
				return null;
		}
	}
	@Override
	public void add(final Loan loan) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO loan(" +
									"member_id, " +
									"book_id, " +
									"loan_date, " +
									"return_date, " +
									"fine" +
							") " +
							"VALUES (?,?,?,?,?)"
						);
						ps.setInt(1, loan.getMember_id());
						ps.setInt(2, loan.getBook_id());
						ps.setDate(3, (Date) loan.getLoan_date());
						ps.setDate(4, (Date) loan.getReturn_date());
						ps.setBigDecimal(5, loan.getFine());
						
						return ps;
					}
				});
	}
	@Override
	public void delete(Loan id) {
		// TODO Auto-generated method stub
	}
	@Override
	public List<Loan> findAll() {
		return jdbcTemplate.query("SELECT * FROM loan ORDER BY member_id", new LoanMapper());
	}
	@Override
	public void update(final Loan loan) {
		
	}
	@Override
	public void save(Loan l) {
		// TODO Auto-generated method stub	
	}
}