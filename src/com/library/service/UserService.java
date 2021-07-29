package com.library.service;

import java.util.List;

import com.library.database.UserDao;
import com.library.model.User;

public class UserService {

	UserDao userDao;

	public UserService() {
		userDao = UserDao.getInstance();
	}

	public void addUser(User user) {

		userDao.addUser(user);
	}

	public User findUser(int userId) {

		return userDao.findUser(userId);
	}

	public List<User> fetchUsers() {

		return userDao.fetchUsers();
	}

}
