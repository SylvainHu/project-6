// 1.2.1 Creation of the account class

package components;

public abstract class Account {

	protected String label;
	protected double balance;
	protected int accountNumber;
	protected Client client;

	private static int numberOfAccounts = 0;

	public Account(String label, Client client) {
		this.label = label;
		this.client = client;
		this.accountNumber = ++numberOfAccounts;
		this.balance = 0.0;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double amount) {
		this.balance = amount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Account number " + accountNumber + ", Label: " + label + ", Balance: " + balance + ", Client: "
				+ client.toString();
	}

}