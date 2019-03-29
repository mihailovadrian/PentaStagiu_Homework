package userUtils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import entity.User;

public class AccountUtils {
	private static String option = "";
	private final static Logger logger = Logger.getLogger(AccountUtils.class.getName());

	public static boolean showAccountMenu(User user) {
		try (Scanner scanIn = new Scanner(System.in)) {
			System.out.println("1.Create Account \n2.Display Account");
			option = scanIn.nextLine().toLowerCase();
			switch (option) {
			case "1":

				break;
			case "2":

				break;

			default:
				logger.fine("Option not avabile.");
				break;

			}
		} catch (InputMismatchException ex) {
			logger.warning(ex.getMessage());
		}

		return false;
	}
}
