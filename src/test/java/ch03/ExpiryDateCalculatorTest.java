package ch03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpiryDateCalculatorTest {

	@Test
	void invalidPayAmount() {
		LocalDate billingDate = LocalDate.of(2024, 11, 18);

		Assertions.assertThrows(IllegalStateException.class, () -> {
			new ExpiryDateCalculator(billingDate, (10000 - 1)).calculateExpiryDate();
		});
		Assertions.assertThrows(IllegalStateException.class, () -> {
			new ExpiryDateCalculator(billingDate, (10000 + 1)).calculateExpiryDate();
		});
		Assertions.assertThrows(IllegalStateException.class, () -> {
			new ExpiryDateCalculator(billingDate, (100_000 + 1)).calculateExpiryDate();
		});
	}

	@Test
	void when_10000payment_first_billing_then_expiry_date_should_1month_later() {

		assertExpiryDate(
			new ExpiryDateCalculator(LocalDate.of(2019, 1, 31), 10_000),
			LocalDate.of(2019, 2, 28)
		);
		assertExpiryDate(
			new ExpiryDateCalculator(LocalDate.of(2020, 1, 31), 10_000),
			LocalDate.of(2020, 2, 29)
		);
		assertExpiryDate(
			new ExpiryDateCalculator(LocalDate.of(2019, 5, 31), 10_000),
			LocalDate.of(2019, 6, 30)
		);
		assertExpiryDate(
			new ExpiryDateCalculator(LocalDate.of(2020, 2, 28), 10_000),
			LocalDate.of(2020, 3, 28)
		);
	}

	@Test
	void when_10000payment_not_first_billing_then_expiry_date_should_1month_later() {
		assertExpiryDate(
			new ExpiryDateCalculator(
				LocalDate.of(2019, 2, 28),
				10_000
			),
			LocalDate.of(2019, 3, 31)
		);
		assertExpiryDate(
			new ExpiryDateCalculator(
				LocalDate.of(2019, 6, 30),
				10_000
			),
			LocalDate.of(2019, 7, 31)
		);
	}

	@Test
	void when_20000payment_then_expiry_date_should_2month_later() {
		assertExpiryDate(
			new ExpiryDateCalculator(
				LocalDate.of(2019, 2, 28),
				20_000
			),
			LocalDate.of(2019, 4, 30)
		);
		assertExpiryDate(
			new ExpiryDateCalculator(
				LocalDate.of(2019, 4, 30),
				30_000
			),
			LocalDate.of(2019, 7, 31)
		);
	}

	@Test
	void when_100000payment_then_expiry_date_should_1year_later() {
		assertExpiryDate(
			new ExpiryDateCalculator(
				LocalDate.of(2020, 2, 29),
				100_000
			),
			LocalDate.of(2021, 2, 28)
		);
	}

	private void assertExpiryDate(ExpiryDateCalculator expiryDateCalculator, LocalDate expectedExpiryDate) {
		assertEquals(expectedExpiryDate, expiryDateCalculator.calculateExpiryDate());
	}
}
