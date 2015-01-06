package ie.cit.tom.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.tom.entity.Book;

public class BookMapper implements RowMapper<Book> {
	public Book mapRow(ResultSet rs, int i) throws SQLException {
		Book b = new Book();
		b.setId(rs.getInt("id"));
		b.setTitle(rs.getString("title"));
		b.setAuthor(rs.getString("author"));
		b.setPublisher(rs.getString("publisher"));
		b.setPublication_date(rs.getDate("publication_date"));
		b.setIsbn(rs.getString("isbn"));
		b.setAvailable(rs.getBoolean("available"));
		return b;
	}
}
