package mainClass;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import entitys.User;
import fileUtils.ReadUserInformation;

public class MainClass {

	public static void main(String[] args) {
		ReadUserInformation userInformation = ReadUserInformation.getInstance();

		boolean logedIn = false;
		User userToLogIn = new User();

		String option = null;
		// try-with-resources
		try (Scanner scanIn = new Scanner(System.in)) {
			if (userInformation != null) {
				while (true) {
					if (!logedIn) {

						System.out.println("User Name: ");
						userToLogIn.setUsername(scanIn.nextLine());
						System.out.println("Password: ");
						userToLogIn.setPassword(scanIn.nextLine());

						if (checkUser(userToLogIn, ReadUserInformation.getUsersInformation())) {
							System.out.println("Welcome user !");
							logedIn = true;
						} else {
							System.out.println("Wrong username/password");
						}

					} else {
						System.out.println("Type -L- if you want to log out OR -X- to Exit: ");
						option = scanIn.nextLine().toLowerCase();
						switch (option) {
						case "l":
							logedIn = false;
							break;
						case "x":
							System.exit(0);
							break;
						default:
							System.out.println("I said -L- \n");
							break;

						}
					}
				}
			}
		} catch (InputMismatchException ex) {
			ex.printStackTrace();
		}
	}

	// check if the user exist in the MAP
	private static boolean checkUser(User user, Map<String, String> infoUsers) {
		boolean result = false;

		if (user != null && infoUsers.containsKey(user.getUsername())) {
			if (infoUsers.get(user.getUsername()).equals(user.getPassword())) {
				result = true;
			}

		}
		return result;

	}

}
