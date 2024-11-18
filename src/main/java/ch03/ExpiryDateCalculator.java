package ch03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

	private static final int FEE = 10_000;
	private static final int MAX_FEE = 100_000;

	private LocalDate billingDate;
	private int payAmount;

	public ExpiryDateCalculator(LocalDate billingDate, int payAmount) {
		this.billingDate = billingDate;
		this.payAmount = payAmount;
	}

	public LocalDate calculateExpiryDate() {
		validatePayAmount();

		int addedMonths = (this.payAmount == MAX_FEE) ? 12 : this.payAmount / FEE;
		LocalDate candidateExpiryDate = billingDate.plusMonths(addedMonths);
		if (isLastDayOfMonth(this.billingDate)) {
			return candidateExpiryDate.withDayOfMonth(getLastDayOfMonth(candidateExpiryDate));
		}
		return candidateExpiryDate;
	}

	private void validatePayAmount() {
		if (this.payAmount > MAX_FEE) {
			throw new IllegalStateException("금액이 너무 많소");
		}
		if (this.payAmount < FEE) {
			throw new IllegalStateException("금액이 너무 적소");
		}
		if (this.payAmount % FEE != 0) {
			throw new IllegalStateException("금액이 이상함");
		}
	}

	private boolean isLastDayOfMonth(LocalDate date) {
		return getLastDayOfMonth(date) == date.getDayOfMonth();
	}

	private int getLastDayOfMonth(LocalDate date) {
		return YearMonth.from(date).lengthOfMonth();
	}
}
