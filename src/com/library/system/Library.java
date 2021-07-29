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

			boolean done = false;
			int choice = 0;
			int bookId, userId;
			String bookName, authorName, publication, status, userName, contactInfo;

			while (!done) {
				System.out.println("1. Add a book");
				System.out.println("2. Find a book");
				System.out.println("3. Issue||return a book");
				System.out.println("4. Get all the book details");
				System.out.println("5. Add a user");
				System.out.println("6. Find a user");
				System.out.println("7. Get all the User details");
				System.out.println("8. Show the records");
				System.out.println("9. Exit");
				System.out.println("Enter ur choice");
				choice = sc.nextInt();

				switch (choice) {
				case 1: // Add a book
					System.out.println("Enter book id : ");
					bookId = sc.nextInt();
					System.out.println("Enter book name : ");
					bookName = sc.next();
					System.out.println("Enter book author : ");
					authorName = sc.next();
					System.out.println("Enter book publication : ");
					publication = sc.next();
					bookService.addBook(new Book(bookId, bookName, authorName, publication));
					System.out.println("Book Added");
					break;

				case 2: // Find a book
					System.out.println("Enter book id : ");
					bookId = sc.nextInt();
					Book book = bookService.findBook(bookId);
					if (book != null) {
						System.out.println(book);
					} else {
						System.out.println("No Book Found");
					}
					break;

				case 3: // Issue||Return a book
					System.out.println("Enter book id : ");
					bookId = sc.nextInt();
					System.out.println("Enter User id :");
					userId = sc.nextInt();
					System.out.println("Enter status :");
					status = sc.next();
					bookService.issueBook(bookId, userId, status);
					if (status.equalsIgnoreCase("Issued")) {
						System.out.println("Book Issued");
					} else {
						System.out.println("Book Returned");
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
					System.out.println("Enter user name : ");
					userName = sc.next();
					System.out.println("Enter contact Number : ");
					contactInfo = sc.next();
					userService.addUser(new User(userId, userName, contactInfo));
					System.out.println("user Added");
					break;

				case 6: // Find a user
					System.out.println("Enter user id to find details: ");
					userId = sc.nextInt();
					User user = userService.findUser(userId);
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

				case 9:// Exit
					done = true;
					break;

				}
			}
			sc.close();
			System.out.println("Bye");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			JDBCConnection.close();
		}

	}

}
