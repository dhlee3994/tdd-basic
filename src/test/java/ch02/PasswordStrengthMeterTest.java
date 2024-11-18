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

	@Test
	void meetsAllCriteria_Then_Strong() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();
		assertEquals(PasswordStrength.STRONG, meter.meter("ab12!@AB"));
		assertEquals(PasswordStrength.STRONG, meter.meter("abc1!Add"));
	}

	@Test
	void meetsOtherCriteria_except_for_Length_Then_Normal() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();
		assertEquals(PasswordStrength.NORMAL, meter.meter("ab12!@A"));
		assertEquals(PasswordStrength.NORMAL, meter.meter("Ab12!c"));
	}

	@Test
	void meetsOtherCriteria_except_for_number_Then_Normal() {
		PasswordStrengthMeter meter = new PasswordStrengthMeter();
		assertEquals(PasswordStrength.NORMAL, meter.meter("ab!@ABqwer"));
	}
}
