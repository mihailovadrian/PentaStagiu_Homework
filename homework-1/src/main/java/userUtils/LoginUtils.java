package userUtils;

import java.util.Map;

import entity.User;

public class LoginUtils {
	// check if the user exist in the MAP
	public static boolean checkUser(User user, Map<String, String> infoUsers) {
		boolean result = false;

		if (user != null && infoUsers.containsKey(user.getUsername())) {
			if (infoUsers.get(user.getUsername()).equals(user.getPassword())) {
				result = true;
			}

		}
		return result;

	}
}
