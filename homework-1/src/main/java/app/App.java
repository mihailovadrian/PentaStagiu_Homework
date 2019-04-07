package app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.AccountDetails;
import entity.User;
import fileUtils.Constants;
import fileUtils.ReadWriteXMLTools;
import menus.AccountMenu;
import menus.PaymentMenu;
import userUtils.LoginUtils;

public class App {
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {

		boolean logged = false;
		boolean exitApp = false;
		User userToLogin = new User();

		ReadWriteXMLTools<AccountDetails> toolsAccount = new ReadWriteXMLTools<>(Constants.ACCOUNT_DETAILS_XML);
		List<AccountDetails> accountDetails = toolsAccount.getInformationResult();
		List<AccountDetails> userAccountDetails = null;
		ReadWriteXMLTools<User> toolsUser = new ReadWriteXMLTools<>(Constants.INPUT_USER_INFORMATION_FILE);
		List<User> users = toolsUser.getInformationResult();
		PaymentMenu payMenu = null;
		String option = null;
		// try-with-resources
		try (Scanner scanIn = new Scanner(System.in)) {
			while (!exitApp) {
				if (!logged) {
					System.out.println("1.Login \n2.Exit");
					option = scanIn.nextLine();

					switch (option) {
					case "1":
						System.out.println("User Name: ");
						userToLogin.setUsername(scanIn.nextLine());
						System.out.println("Password: ");
						userToLogin.setPassword(scanIn.nextLine());

						if (LoginUtils.checkUser(userToLogin, users)) {
							logged = true;
							userAccountDetails = AccountMenu.getUserAccounts(userToLogin, accountDetails);
						}
						break;
					case "2":
						exitApp = true;
						break;
					default:
						LOGGER.warning("Option not avabile.");
						break;

					}
				} else {
					System.out.println("1.Account \n2.Payment \n3.Logout");
					option = scanIn.nextLine();
					switch (option) {
					case "1":
						logged = AccountMenu.showAccountMenu(userToLogin, scanIn, userAccountDetails);
						break;
					case "2":
						payMenu = new PaymentMenu(userAccountDetails);
						logged = payMenu.showPaymentMenu(scanIn);
						break;

					case "3":
						logged = false;
						break;

					default:
						LOGGER.warning("Option not avabile.");
						break;

					}
				}
			}

		} catch (InputMismatchException ex) {
			LOGGER.log(Level.SEVERE, "Exception occur ", ex);
		}
	}

}
