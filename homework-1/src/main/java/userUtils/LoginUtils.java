package userUtils;

import java.util.Map;
import java.util.logging.Logger;

import entity.User;

public class LoginUtils {
	private final static Logger logger = Logger.getLogger(LoginUtils.class.getName());

	public static boolean checkUser(User user, Map<String, String> infoUsers) {

		if (user != null && infoUsers.containsKey(user.getUsername())) {
			if (infoUsers.get(user.getUsername()).equals(user.getPassword())) {
				logger.info("Succesfull login !");
				return true;
			}

		}
		logger.info("Wrong Username/Password!");
		return false;

	}
}
