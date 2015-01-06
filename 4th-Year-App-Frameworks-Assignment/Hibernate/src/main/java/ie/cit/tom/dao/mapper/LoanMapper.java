package ie.cit.tom.dao.mapper;

import ie.cit.tom.entity.Loan;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LoanMapper implements RowMapper<Loan> {
	public Loan mapRow(ResultSet rs, int i) throws SQLException {
		Loan l = new Loan();
		l.setId(rs.getInt("id"));
		l.setMember_id(rs.getInt("member_id"));
		l.setBook_id(rs.getInt("book_id"));
		l.setLoan_date(rs.getDate("loan_date"));
		l.setReturn_date(rs.getDate("return_date"));
		l.setFine(rs.getBigDecimal("fine"));
		return l;
	}
}