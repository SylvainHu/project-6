// 1.3.3 Creation of the Transfert, Credit, Debit classes

package components;

import java.time.LocalDate;

public class Debit extends Flow {

	public Debit(String comment, double amount, int targetAccountNumber, boolean effect, LocalDate dateOfFlow) {
		super(comment, amount, targetAccountNumber, effect, dateOfFlow);
	}

}
