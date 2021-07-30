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
import com.library.model.Record;

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

	RecordDao recordDao = RecordDao.getInstance();

	// Adding a book in table Book
	public void addBook(Book book) {

		try {

			Connection con = JDBCConnection.connect();
			PreparedStatement pst = con.prepareStatement("insert into book values(?,?,?,?,?)");
			pst.setInt(1, book.getBookId());
			pst.setString(2, book.getBookName());
			pst.setString(3, book.getAuthorName());
			pst.setString(4, book.getPublication());
			pst.setString(5, book.getAvailabilty());

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {

			System.out.println("Error in adding the book ");
			e.printStackTrace();
		}

	}

	// Find a book in table book
	public Book findBook(int bookId) {

		try {

			Connection con = JDBCConnection.connect();

			PreparedStatement pst = con.prepareStatement("Select * from book where bookId= ?");
			pst.setInt(1, bookId);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {

			int id = rs.getInt(1);
			String bookName = rs.getString("bookName");
			String authorName = rs.getString("authorName");
			String publication = rs.getString("publication");
			String availabilty = rs.getString("availabilty");
			
			Book b = new Book(id, bookName, authorName, publication, availabilty);
			return b;
		}

		}catch(Exception e)
	{

			System.out.println("Error in Finding the book ");
			e.printStackTrace();
		}return null;
	}

	// Issue a book from books
	public void issueBook(int bookId, int userId) {

		try {

			Connection con = JDBCConnection.connect();

			String availabilty = "no";
			PreparedStatement pst = con.prepareStatement("update book set availabilty = ? where bookId =? ");
			pst.setString(1, availabilty);
			pst.setInt(2, bookId);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			Timestamp date = new Timestamp(System.currentTimeMillis());

			Record record = new Record();
			record.setUserId(userId);
			record.setBookId(bookId);
			record.setStatus("Issued");
			record.setDate(date);

			recordDao.addRecord(record);

			con.commit();

		} catch (Exception e) {

			System.out.println("Error in Issueing the book ");
			e.printStackTrace();
		}

	}

	// Display all the book details
	public List<Book> bookDetails() {
		try {

			Connection con = JDBCConnection.connect();

			Statement st = con.createStatement();
			String sql = "select * from book";
			ResultSet rs = st.executeQuery(sql);

			List<Book> books = new ArrayList<Book>();
			while (rs.next()) {

				int id = rs.getInt("bookId");
				String bookName = rs.getString("bookName");
				String authorName = rs.getString("authorName");
				String publication = rs.getString("publication");
				String availabilty = rs.getString("availabilty");
				Book b = new Book(id, bookName, authorName, publication, availabilty);
				books.add(b);
			}
			return books;

		} catch (SQLException e) {

			System.out.println("Error in Finding the book details ");
			e.printStackTrace();
		}
		return null;
	}

	// Return a book
	public void returnBook(int bookId, int userId) {

		try {

			Connection con = JDBCConnection.connect();

			String availabilty = "Yes";
			PreparedStatement pst = con.prepareStatement("update book set availabilty = ? where bookId =? ");
			pst.setString(1, availabilty);
			pst.setInt(2, bookId);

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			Timestamp date = new Timestamp(System.currentTimeMillis());

			Record record = new Record();
			record.setUserId(userId);
			record.setBookId(bookId);
			record.setStatus("Returned");
			record.setDate(date);

			recordDao.addRecord(record);

			con.commit();

		} catch (Exception e) {

			System.out.println("Error in Returning the book ");
			e.printStackTrace();
		}

	}
}
