package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import appUtils.LoginUtils;
import entity.User;
import fileUtils.ReadUserInformation;

public class App {

	public static void main(String[] args) {
		ReadUserInformation userInformation = ReadUserInformation.getInstance();

		boolean logedIn = false;
		boolean exitApp = false;
		User userToLogIn = new User();

		String option = null;
		// try-with-resources
		try (Scanner scanIn = new Scanner(System.in)) {
			if (userInformation != null) {
				while (!exitApp) {
					if (!logedIn) {

						System.out.println("User Name: ");
						userToLogIn.setUsername(scanIn.nextLine());
						System.out.println("Password: ");
						userToLogIn.setPassword(scanIn.nextLine());

						if (LoginUtils.checkUser(userToLogIn, ReadUserInformation.getUsersInformation())) {
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
							exitApp = true;
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

}
