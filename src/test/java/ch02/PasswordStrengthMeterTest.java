package ch02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * 아래의 조건 중 3개가 일치하면 강함, 2개가 일치하면 보통, 1개이하가 일치하면 약함
 * - 길이가 8글자 이상
 * - 0부터 9사이의 숫자를 포함
 * - 대문자 포함
 */

class PasswordStrengthMeterTest {

	private PasswordStrengthMeter meter = new PasswordStrengthMeter();

	@Test
	void meetsAllCriteria_Then_Strong() {
		assertStrength("ab12!@AB", PasswordStrength.STRONG);
		assertStrength("abc1!Add", PasswordStrength.STRONG);
	}

	@Test
	void meetsOtherCriteria_except_for_Length_Then_Normal() {
		assertStrength("ab12!@A", PasswordStrength.NORMAL);
		assertStrength("Ab12!c", PasswordStrength.NORMAL);
	}

	@Test
	void meetsOtherCriteria_except_for_number_Then_Normal() {
		assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
	}

	private void assertStrength(String password, PasswordStrength expectedStrength) {
		assertEquals(expectedStrength, meter.meter(password));
	}
}
