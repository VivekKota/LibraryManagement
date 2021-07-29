package com.library.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.library.model.Book;

public class BookDao {

	private static BookDao bookDao;

	private BookDao() {
	}

	public static BookDao getInstance() {

		if (bookDao == null) {
			bookDao = new BookDao();
		}
		return bookDao;
	}

	// Adding a book in table Book
	public void addBook(Book book) {

		try {

			Connection con = JDBCConnection.connect();
			PreparedStatement pst = con.prepareStatement("insert into book values(?,?,?,?)");
			pst.setInt(1, book.getBookId());
			pst.setString(2, book.getBookName());
			pst.setString(3, book.getAuthorName());
			pst.setString(4, book.getPublication());

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");
	
		

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Find a book in table book
	public Book findBook(int bookId) {

		try {

			Connection con = JDBCConnection.connect();;
			PreparedStatement pst = con.prepareStatement("Select * from book where bookId= ?");
			pst.setInt(1, bookId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String bookName = rs.getString("bookName");
				String authorName = rs.getString("authorName");
				String publication = rs.getString("publication");
				Book b = new Book(id, bookName, authorName, publication);
				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Issue a book from books
	public void issueBook(int bookId, int userId, String status) {

		try {

			Connection con = JDBCConnection.connect();;

			if (status.equalsIgnoreCase("Issued")) {
				PreparedStatement pst = con.prepareStatement("delete from book where bookId =? ");
				pst.setInt(1, bookId);

				int rows = pst.executeUpdate();
				System.out.println(rows + " rows updated");
			}

			Timestamp date = new Timestamp(System.currentTimeMillis());

			PreparedStatement pst2 = con
					.prepareStatement("insert into record(userId,bookId,status,date) values(?,?,?,?)");
			pst2.setInt(1, userId);
			pst2.setInt(2, bookId);
			pst2.setString(3, status);
			pst2.setTimestamp(4, date);

			int rows2 = pst2.executeUpdate();
			System.out.println(rows2 + " rows updated");

			try {
				con.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Display all the book details
	public List<Book> bookDetails() {
		try {

			Connection con = JDBCConnection.connect();;
			Statement st = con.createStatement();
			String sql = "select * from book";
			ResultSet rs = st.executeQuery(sql);
			List<Book> books = new ArrayList<Book>();
			while (rs.next()) {

				int id = rs.getInt(1);
				String bookName = rs.getString("bookName");
				String authorName = rs.getString("authorName");
				String publication = rs.getString("publication");
				Book b = new Book(id, bookName, authorName, publication);
				books.add(b);
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
