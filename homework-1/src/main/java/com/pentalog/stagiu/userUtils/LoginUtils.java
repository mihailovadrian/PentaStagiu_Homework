package com.pentalog.stagiu.userUtils;

import java.util.List;

import java.util.logging.Logger;

import com.pentalog.stagiu.entity.User;


public class LoginUtils {
	private final static Logger logger = Logger.getLogger(LoginUtils.class.getName());

	public static boolean checkUser(User user, List<User> users) {

		for (User u : users) {
			if (u.equals(user))
				return true;
		}
		logger.warning("Wrong Username/Password!");
		return false;

	}
}
