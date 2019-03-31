package userUtils;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import entity.AccountDetails;
import entity.User;
import fileUtils.AccountDetailsTools;
import fileUtils.Constants;

public class AccountUtils {
	private static String option = "";
	private final static Logger logger = Logger.getLogger(AccountUtils.class.getName());
	private static List<AccountDetails> accountDetails;

	public static boolean showAccountMenu(User user, Scanner scanner) {
		AccountDetailsTools tools = AccountDetailsTools.getInstance();
		accountDetails = AccountDetailsTools.getResult();
		if (tools != null) {
			System.out.println("1.Create Account \n2.Display Account");
			option = scanner.nextLine().toLowerCase();
			switch (option) {
			case "1":
				if (createNewAccount(user, accountDetails, scanner))
					return true;
			case "2":
				for (AccountDetails account : accountDetails) {

					if (account.getUsername().equals(user.getUsername())) {
						System.out.println(account.toString());
					}
				}
				return true;
			default:
				logger.fine("Option not avabile.");
				return true;

			}
		}
		return false;

	}

	private static boolean createNewAccount(User user, List<AccountDetails> accountDetails, Scanner scanner) {
		AccountDetails newAccountDetails = new AccountDetails();
		String accountNumber = "";
		boolean informationCorrect = false;

		while (!informationCorrect) {
			logger.info("What account type do you want to create ? (Euro/Ron)");
			option = scanner.nextLine().toLowerCase();

			switch (option) {
			case "euro":
				accountNumber = Constants.CURRENCY_EURO;
				newAccountDetails.setAccountType(Constants.CURRENCY_EURO);
				break;

			case "ron":
				accountNumber = Constants.CURRENCY_RON;
				newAccountDetails.setAccountType(Constants.CURRENCY_RON);
				break;

			default:
				logger.warning("Account type incorrect !");
				break;

			}
			logger.info("Enter your account number ! It will require 22 digits/characters");
			option = scanner.nextLine().toLowerCase();

			if (option.length() == 22) {
				accountNumber += option;
				newAccountDetails.setAccountNumber(String.valueOf(accountNumber));
				newAccountDetails.setUsername(user.getUsername());

			} else
				logger.warning("Not enough characters !");
			// condition to check if account information are correct , else we repeat
			// reading information from the console
			if (accountNumber.length() == 24 && (accountNumber.substring(0, 2).equals(Constants.CURRENCY_EURO)
					|| accountNumber.substring(0, 2).equals(Constants.CURRENCY_RON))) {
				informationCorrect = true;
				logger.info("Informations are correct !");
			}
		}
		if (newAccountDetails != null)
			accountDetails.add(newAccountDetails);
		if (AccountDetailsTools.writeAccountDetailsToXML(accountDetails))
			return true;
		return false;
	}

}
