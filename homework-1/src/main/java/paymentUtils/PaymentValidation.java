package paymentUtils;

import entity.AccountDetails;
import exceptions.AccountTypeException;

public class PaymentValidation {
	public boolean differentAccount(AccountDetails accountToTransfer, AccountDetails userInputAccount) {
		if (accountToTransfer.equals(userInputAccount))
			throw new AccountTypeException("Can not transfer to the same account!");
		return true;
	}

	public boolean sameAccountType(AccountDetails accountToTransfer, AccountDetails userInputAccount) {

		if (accountToTransfer.getAccountType().equals(userInputAccount.getAccountType()))
			return true;

		throw new AccountTypeException("Not same account type !");

	}
}
