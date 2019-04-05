package entity;

import java.math.BigDecimal;

public class AccountDetails {
	private String accountNumber;
	private String username;
	private BigDecimal balance;
	private String accountType;

	public AccountDetails() {

		this.accountNumber = "";
		this.username = "";
		this.balance = new BigDecimal("0.0");
		this.accountType = "";
	}

	public AccountDetails(String accountNumber, String username, double balance, String accountType) {

		this.accountNumber = accountNumber;
		this.username = username;
		this.balance = new BigDecimal(balance);
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDetails other = (AccountDetails) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Account Details --> Account Number=" + accountNumber + " of the  username :" + username + "\n Balance : " + balance
				+ ", account type :" + accountType+"\n";
	}
}
