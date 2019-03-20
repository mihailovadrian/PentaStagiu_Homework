package mainClass;

import java.util.Map;
import java.util.Scanner;

import entitys.User;
import fileUtils.FileUtils;

public class MainClass {

	private static Scanner scanIn;

	public static void main(String[] args) {

		// PATH -ul E STATIC !!!!
		Map<String, String> usersInformation = FileUtils.readUserInfoFromFile(Constants.inputFilePath);

		boolean logedIn = false;
		User userToLogIn = new User();
		scanIn = new Scanner(System.in);
		String option = null;

		if (usersInformation != null) {
			while (true) {
				if (logedIn == false) {

					System.out.println("User Name: ");
					userToLogIn.setUsername(scanIn.nextLine());
					System.out.println("Password: ");
					userToLogIn.setPassword(scanIn.nextLine());

					if (CheckUser(userToLogIn, usersInformation)) {
						System.out.println("Welcome user !");
						logedIn = true;
					} else {
						System.out.println("Wrong username/password");
					}

				} else {
					System.out.println("Type -L- if you want to log out: ");
					option = scanIn.nextLine().toLowerCase();
					switch (option) {
					case "l":
						logedIn = false;
						break;
					default:
						System.out.println("I said -L- \n");
						break;

					}
				}
			}
		}
	}

	// check if the user exist in MAP
	private static boolean CheckUser(User user, Map<String, String> infoUsers) {
		boolean result = false;

		if (user != null && infoUsers.containsKey(user.getUsername())) {
			if (infoUsers.get(user.getUsername()).equals(user.getPassword())) {
				result = true;
			}

		}
		return result;

	}

}
