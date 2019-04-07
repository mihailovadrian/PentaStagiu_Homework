package com.pentalog.stagiu.menus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.pentalog.stagiu.entity.AccountDetails;

public class PaymentMenu {

	private static String option = null;
	private static final Logger LOGGER = Logger.getLogger(AccountMenu.class.getName());

	private List<AccountDetails> accountDetails;

	public PaymentMenu(List<AccountDetails> accountDetails) {
		super();
		this.accountDetails = accountDetails;
	}

	public boolean showPaymentMenu(Scanner scanner) {
		AccountDetails accountToTransfer;
		BigDecimal subtractResult;
		BigDecimal addResult = null;
		String oldBalance = null;
		System.out.println("Please  select one of the accounts");
		showAccount(accountDetails);
		try {
			if (scanner.hasNextInt()) {
				option = scanner.nextLine();
				accountToTransfer = accountDetails.get(Integer.parseInt(option));

			} else
				return invalidInputMessage("Invalid account");

			System.out.println("Select the amount of money which you want to transfer");
			if (scanner.hasNextBigDecimal()) {
				option = scanner.nextLine();
				oldBalance = accountToTransfer.getBalance();
				subtractResult = accountToTransfer.getBalanceDecimal().subtract(new BigDecimal(option));
				if (subtractResult.compareTo(BigDecimal.ZERO) > 0) {
					accountToTransfer.setBalance(subtractResult);
					addResult = new BigDecimal(option);
				} else
					return invalidInputMessage("Not enough money in this account ");
			} else
				return invalidInputMessage("Amount of money in incorrect format");

			System.out.println("Please  select one of the accounts which you want to transfer");
			showAccount(accountDetails);

			if (scanner.hasNextInt()) {
				option = scanner.nextLine();

				if (differentAccount(accountToTransfer, option) && sameAccountType(accountToTransfer, option)) {
					accountToTransfer = accountDetails.get(Integer.parseInt(option));
					accountToTransfer.setBalance(accountToTransfer.getBalanceDecimal().add(addResult));
				} else {
					accountToTransfer.setBalance(oldBalance);
					return invalidInputMessage("You can transfer to the same account / or different account type");
				}
			} else
				return invalidInputMessage("Invalid option");
			return invalidInputMessage("Show paymentmoney EXIT");

		} catch (IndexOutOfBoundsException e) {
			return invalidInputMessage("Cont neexistent");
		}
	}

	private void showAccount(List<AccountDetails> accounts) {
		int i = 0;
		for (AccountDetails account : accounts) {
			System.out.println(i + ". " + account.getAccountNumber() + " ---- " + account.getBalance());
			i++;
		}
	}

	private boolean invalidInputMessage(String msg) {
		LOGGER.warning(msg);
		return true;
	}

	private boolean differentAccount(AccountDetails accountToTransfer, String userInputAccount) {
		if (accountToTransfer.equals(accountDetails.get(Integer.parseInt(userInputAccount))))
			return false;
		return true;
	}

	private boolean sameAccountType(AccountDetails accountToTransfer, String userInputAccount) {
		if (accountToTransfer.getAccountType()
				.equals(accountDetails.get(Integer.parseInt(userInputAccount)).getAccountType()))
			return true;
		return false;
	}

}
