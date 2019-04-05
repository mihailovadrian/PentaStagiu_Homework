package userUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import entity.AccountDetails;
import entity.User;
import fileUtils.AccountDetailsTools;
import fileUtils.Constants;

public class AccountUtils {
	private static String option = null;
	private static final Logger LOGGER = Logger.getLogger(AccountUtils.class.getName());

	public static boolean showAccountMenu(User user, Scanner scanner) {
		AccountDetailsTools tools = AccountDetailsTools.getInstance();
		List<AccountDetails> accountDetails = tools.getResultAccountDetails();

		if (tools != null) {
			System.out.println("1.Create Account \n2.Display Account");
			option = scanner.nextLine().toLowerCase();

			switch (option) {
			case "1":
				if (createNewAccount(user, accountDetails, scanner))
					return true;
				else
					LOGGER.log(Level.SEVERE, "The new account could not be created");
			case "2":
				for (AccountDetails account : accountDetails) {

					if (account.getUsername().equals(user.getUsername())) {
						System.out.println(account.toString());
					}
				}
				return true;
			default:
				LOGGER.info("Option not avabile.");
				break;

			}
		}
		return false;

	}

	private static boolean createNewAccount(User user, List<AccountDetails> accountDetails, Scanner scanner) {
		AccountDetails newAccountDetails = new AccountDetails();
		String accountNumber = "";
		AccountDetailsTools tools = AccountDetailsTools.getInstance();
		boolean informationCorrect = false;

		while (!informationCorrect) {
			LOGGER.info("What account type do you want to create ? (Euro/Ron)");
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
				LOGGER.warning("Account type incorrect !");
				break;

			}
			LOGGER.info("Enter your account number ! It will require 22 digits/characters");
			option = scanner.nextLine().toLowerCase();

			if (option.length() == 22) {
				accountNumber += option;
				newAccountDetails.setAccountNumber(String.valueOf(accountNumber));
				newAccountDetails.setUsername(user.getUsername());

			} else
				LOGGER.warning("Not enough characters !");

			// condition to check if account information are correct , else we repeat
			// reading information from the console

			if (accountNumber.length() == 24 && (accountNumber.substring(0, 2).equals(Constants.CURRENCY_EURO)
					|| accountNumber.substring(0, 2).equals(Constants.CURRENCY_RON))) {
				informationCorrect = true;
				LOGGER.info("Informations are correct !");
			}
			LOGGER.info("Enter the amount of money that you want to have ");

			if (scanner.hasNextBigDecimal()) {
				option = scanner.nextLine();
				newAccountDetails.setBalance(new BigDecimal(option));
				LOGGER.info(newAccountDetails.getBalance().toString());
			} else {
				LOGGER.warning("Amount of money incorrect");
				informationCorrect = false;
			}
		}
		if (newAccountDetails != null)
			accountDetails.add(newAccountDetails);
		if (tools.writeAccountDetailsToXML(accountDetails))
			return true;
		return false;
	}

}
