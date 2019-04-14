package com.pentalog.stagiu.utils;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pentalog.stagiu.App;
import com.pentalog.stagiu.exceptions.CheckUserValidationException;

import entity.User;

public class UserUtils {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());

	public static boolean checkUser(User user, List<User> users) {
		try {
			for (User u : users) {
				if (u.equals(user))
					return true;
			}
			throw new CheckUserValidationException("Username/Password incorrect !");
		} catch (CheckUserValidationException e) {

			LOGGER.log(Level.SEVERE, "Exception occur", e);
		}
		return false;

	}
}
