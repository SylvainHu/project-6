// 1.3.3 Creation of the Transfert, Credit, Debit classes

package components;

import java.time.LocalDate;

public class Transfert extends Flow {

	private int issuingAccountNumber;

	public Transfert(String comment, double amount, int targetAccountNumber, boolean effect, LocalDate dateOfFlow,
			int issuingAccountNumber) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
		this.issuingAccountNumber = issuingAccountNumber;
	}

	public int getIssuingAccountNumber() {
		return issuingAccountNumber;
	}

	public void setIssuingAccountNumber(int issuingAccountNumber) {
		this.issuingAccountNumber = issuingAccountNumber;
	}
}
