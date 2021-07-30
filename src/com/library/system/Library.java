package com.library.system;

import java.util.List;
import java.util.Scanner;

import com.library.database.JDBCConnection;
import com.library.model.Book;
import com.library.model.Record;
import com.library.model.User;
import com.library.service.BookService;
import com.library.service.RecordService;
import com.library.service.UserService;

public class Library {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {

			BookService bookService = new BookService();
			UserService userService = new UserService();
			RecordService recordsService = new RecordService();

			Book book = new Book();
			User user = new User();
			Record record = new Record();

			boolean done = false;
			int choice = 0;
			int bookId, userId;
			String bookName, authorName, publication, userName, contactInfo, availabilty;

			while (!done) {
				System.out.println("1. Add a book");
				System.out.println("2. Find a book");
				System.out.println("3. Issue a book");
				System.out.println("4. Get all the book details");
				System.out.println("5. Add a user");
				System.out.println("6. Find a user");
				System.out.println("7. Get all the User details");
				System.out.println("8. Show the records");
				System.out.println("9. Return a book");
				System.out.println("10. Books Borrowed by each User");
				System.out.println("11. Exit");
				System.out.println("Enter ur choice");
				choice = sc.nextInt();

				switch (choice) {
				case 1: // Add a book
					System.out.println("Enter book id : ");
					bookId = sc.nextInt();
					book = bookService.findBook(bookId);
					if (book == null) {
						System.out.println("Enter book name : ");
						bookName = sc.next();
						System.out.println("Enter book author : ");
						authorName = sc.next();
						System.out.println("Enter book publication : ");
						publication = sc.next();
						availabilty = "yes";
						bookService.addBook(new Book(bookId, bookName, authorName, publication, availabilty));
						System.out.println("Book Added");
					} else {
						System.out.println("booKId already present");
					}
					break;

				case 2: // Find a book
					System.out.println("Enter book id : ");
					bookId = sc.nextInt();
					book = bookService.findBook(bookId);
					if (book != null) {
						System.out.println(book);
					} else {
						System.out.println("No Book Found");
					}
					break;

				case 3: // Issue a book
					System.out.println("Enter book id : ");
					bookId = sc.nextInt();
					book = bookService.findBook(bookId);
					if (book != null) {

						if (book.getAvailabilty().equalsIgnoreCase("yes")) {

							System.out.println("Enter User id :");
							userId = sc.nextInt();

							user = userService.findUser(userId);
							if (user != null) {

								record =recordsService.findLatestRecord(userId);
								if(record == null || record.getStatus().equalsIgnoreCase("Received"))
								{
								  bookService.issueBook(bookId, userId);
								  System.out.println("Book Issued");
								
								}else {
									System.out.println("Already a book is Issued on this userId");
								}

							} else {
								System.out.println("No User Found");
							}

						} else {
							System.out.println("Book is lend by someone");
						}

					} else {
						System.out.println("Book is not present in library");
					}

					break;

				case 4: // Get all the book details
					List<Book> books = bookService.bookDetails();
					for (int i = 0; i < books.size(); i++) {
						System.out.println(books.get(i));
					}
					break;

				case 5: // Add a user
					System.out.println("Enter user id : ");
					userId = sc.nextInt();
					User user2 = userService.findUser(userId);
					if (user2 == null) {
						System.out.println("Enter user name : ");
						userName = sc.next();
						System.out.println("Enter contact Number : ");
						contactInfo = sc.next();
						userService.addUser(new User(userId, userName, contactInfo));
						System.out.println("user Added");

					} else {
						System.out.println("user already present");
					}
					break;

				case 6: // Find a user
					System.out.println("Enter user id to find details: ");
					userId = sc.nextInt();
					user = userService.findUser(userId);
					if (user != null) {
						System.out.println(user);
					} else {
						System.out.println("No User Found");
					}
					break;

				case 7: // Get all the User details
					List<User> users = userService.fetchUsers();
					for (int i = 0; i < users.size(); i++) {
						System.out.println(users.get(i));
					}
					break;

				case 8:// show the Records
					List<Record> records = recordsService.fetchRecords();
					for (int i = 0; i < records.size(); i++) {
						System.out.println(records.get(i));
					}
					break;

				case 9:// Return a book

					System.out.println("Enter book id : ");
					bookId = sc.nextInt();

					book = bookService.findBook(bookId);
					
					if (book != null) {
						
						if (book.getAvailabilty().equalsIgnoreCase("no")) {

							System.out.println("Enter User id :");
							userId = sc.nextInt();
							user = userService.findUser(userId);
							
							if (user != null) {

								record = recordsService.findLatestRecord(userId);
								
								if ( record != null) {

									bookService.returnBook(bookId, userId);
									System.out.println("Book Returned");

								} else {
									System.out.println("This bookId is not issued on your userId");
								}

							} else {
								System.out.println("No User Found. Enter correct UserId");
							}

						} else {
							System.out.println("Duplicate bookId Found. Enter correct bookId");
						}

					} else {
						System.out.println("No existing record of bookId is found. Enter correct BookId");
					}
					break;
					
				case 10 : // Books Borrowed by each User
					
					System.out.println("Enter User id :");
					userId = sc.nextInt();
					user = userService.findUser(userId);
					
					if (user != null) {

						List<Record> records1 = recordsService.findUserRecords(userId);
						
						for (int i = 0; i < records1.size(); i++) {
							System.out.println(records1.get(i));
						}
						System.out.println("Reocrds present are shown");


					} else {
						System.out.println("No User Found on this UserId");
					}
					
					break;

				case 11:// Exit
					done = true;
					break;

				}
			}
			sc.close();
			System.out.println("Bye");

		} catch (

		Exception e) {

			System.out.println("Error in the main block ");
			e.printStackTrace();
		} finally {
			JDBCConnection.close();
		}

	}

}
