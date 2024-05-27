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
		this.balance = 0.0;
		this.accountNumber = ++numberOfAccounts;
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

//	1.3.2 Creation of the Flow class
//	1.3.5 Updating accounts
	public void setBalance(Flow flow) {
		if (flow instanceof Credit) {
			this.balance += flow.getAmount();
		} else if (flow instanceof Debit) {
			this.balance -= flow.getAmount();
		} else if (flow instanceof Transfert) {
			Transfert transfer = (Transfert) flow;
			if (this.accountNumber == transfer.getTargetAccountNumber()) {
				this.balance += transfer.getAmount();
			}
			if (this.accountNumber == transfer.getIssuingAccountNumber()) {
				this.balance -= transfer.getAmount();
			}
		}
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
