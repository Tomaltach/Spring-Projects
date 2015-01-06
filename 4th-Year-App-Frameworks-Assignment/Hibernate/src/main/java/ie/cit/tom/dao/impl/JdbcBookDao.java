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

import ie.cit.tom.dao.BookDao;
import ie.cit.tom.dao.mapper.BookMapper;
import ie.cit.tom.entity.Book;

public class JdbcBookDao implements BookDao {
	private JdbcTemplate jdbcTemplate;
	
	public JdbcBookDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Book getById(int id) {
		try {
			return jdbcTemplate.queryForObject(
					"SELECT * FROM book WHERE id = ?",
					new BookMapper(),
					id);
		}
		catch (EmptyResultDataAccessException e) {
				return null;
		}
	}
	@Override
	public void add(final Book book) {
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(
							Connection connection) throws SQLException {
						PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO book(" +
									"title, " +
									"author, " +
									"publisher, " +
									"publication_date, " +
									"isbn, " +
									"available" +
							") " +
							"VALUES (?,?,?,?,?,?)"
						);
						ps.setString(1, book.getTitle());
						ps.setString(2, book.getAuthor());
						ps.setString(3, book.getPublisher());
						ps.setDate(4, (Date) book.getPublication_date());
						ps.setString(5, book.getIsbn());
						ps.setBoolean(6, book.isAvailable());
						
						return ps;
					}
				});
	}
	@Override
	public void delete(Book id) {
		// TODO Auto-generated method stub
	}
	@Override
	public List<Book> findAll() {
		return jdbcTemplate.query("SELECT * FROM book", new BookMapper());
	}
	@Override
	public void update(Book b) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void save(Book b) {
		// TODO Auto-generated method stub	
	}
}