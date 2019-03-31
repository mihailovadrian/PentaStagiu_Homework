package app;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import entity.User;
import fileUtils.ReadUserInformation;
import userUtils.AccountUtils;
import userUtils.LoginUtils;

public class App {
	private final static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		ReadUserInformation userInformation = ReadUserInformation.getInstance();

		boolean logedIn = false;
		boolean exitApp = false;
		User userToLogin = new User();

		String option = null;
		// try-with-resources
		try (Scanner scanIn = new Scanner(System.in)) {
			if (userInformation != null) {
				while (!exitApp) {
					if (!logedIn) {
						System.out.println("1.Login \n2.Exit");
						option = scanIn.nextLine();

						switch (option) {
						case "1":
							System.out.println("User Name: ");
							userToLogin.setUsername(scanIn.nextLine());
							System.out.println("Password: ");
							userToLogin.setPassword(scanIn.nextLine());

							if (LoginUtils.checkUser(userToLogin, ReadUserInformation.getUsersInformation())) {
								logedIn = true;
							}
							break;
						case "2":
							exitApp = true;
							break;
						default:
							logger.fine("Option not avabile.");
							break;

						}
					} else {
						System.out.println("1.Account \n2.Logout");
						option = scanIn.nextLine();
						switch (option) {
						case "1":
							logedIn = AccountUtils.showAccountMenu(userToLogin, scanIn);
							break;
						case "2":
							logedIn = false;
							break;

						default:
							logger.fine("Option not avabile.");
							break;

						}
					}
				}
			}
		} catch (InputMismatchException ex) {
			logger.warning(ex.getMessage());
		}
	}

}
