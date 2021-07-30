package com.library.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.model.User;

public class UserDao {

	private static UserDao userDao;

	private UserDao() {
	}

	public static UserDao getInstance() {

		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	// Add a user into user table
	public void addUser(User user) {

		try {

			Connection con = JDBCConnection.connect();

			PreparedStatement pst = con.prepareStatement("insert into user values(?,?,?)");
			pst.setInt(1, user.getUserId());
			pst.setString(2, user.getUserName());
			pst.setString(3, user.getContactInfo());

			int rows = pst.executeUpdate();
			System.out.println(rows + " rows updated");

			con.commit();

		} catch (Exception e) {

			System.out.println("Error in adding the user ");
			e.printStackTrace();
		}

	}

	// Find a user from user table
	public User findUser(int userId) {

		try {

			Connection con = JDBCConnection.connect();
			PreparedStatement pst = con.prepareStatement("select * from user where userId = ?");
			pst.setInt(1, userId);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				int id = rs.getInt(1);
				String userName = rs.getString("userName");
				String contactInfo = rs.getString("contactInfo");

				User user = new User(id, userName, contactInfo);
				return user;
			}
			return null;

		} catch (Exception e) {

			System.out.println("Error in Finding the user ");
			e.printStackTrace();
		}
		return null;
	}

	// Show all the user Details
	public List<User> fetchUsers() {

		try {

			Connection con = JDBCConnection.connect();

			Statement st = con.createStatement();
			String sql = "Select * from User";
			ResultSet rs = st.executeQuery(sql);

			List<User> users = new ArrayList<User>();
			while (rs.next()) {

				int userId = rs.getInt("userId");
				String userName = rs.getString("userName");
				String contactInfo = rs.getString("contactInfo");
				User user = new User(userId, userName, contactInfo);
				users.add(user);
			}
			return users;

		} catch (Exception e) {

			System.out.println("Error in fetching the user details ");
			e.printStackTrace();
		}
		return null;

	}
}
