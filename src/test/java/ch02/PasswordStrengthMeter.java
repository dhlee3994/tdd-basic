package ch02;

import java.util.Objects;

/**
 * 아래의 조건 중 3개가 일치하면 강함, 2개가 일치하면 보통, 1개이하가 일치하면 약함
 * - 길이가 8글자 이상
 * - 0부터 9사이의 숫자를 포함
 * - 대문자 포함
 */
public class PasswordStrengthMeter {

	public PasswordStrength meter(String password) {
		Objects.requireNonNull(password);

		if (password.length() < 8) {
			return PasswordStrength.NORMAL;
		}

		boolean containsNumber = meetsContainingNumberCriteria(password);
		if (!containsNumber) {
			return PasswordStrength.NORMAL;
		}

		return PasswordStrength.STRONG;
	}

	private static boolean meetsContainingNumberCriteria(String password) {
		return password.chars().anyMatch(Character::isDigit);
	}
}
