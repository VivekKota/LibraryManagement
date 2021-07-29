package com.library.service;

import java.util.List;

import com.library.database.BookDao;
import com.library.model.Book;

public class BookService {

	BookDao bookDao;
	
	public BookService()
	{
		bookDao=BookDao.getInstance();
	}

	
	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	public Book findBook(int bookId) {
		
		return bookDao.findBook(bookId);
	}

	public void issueBook(int bookId, int userId,String status) {
		
		bookDao.issueBook(bookId,userId,status);
	}

	public List<Book> bookDetails() {
		
		return bookDao.bookDetails();
	}

}
