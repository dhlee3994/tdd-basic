package ch02;

/**
 * 아래의 조건 중 3개가 일치하면 강함, 2개가 일치하면 보통, 1개이하가 일치하면 약함
 * - 길이가 8글자 이상
 * - 0부터 9사이의 숫자를 포함
 * - 대문자 포함
 */
public class PasswordStrengthMeter {

	public PasswordStrength meter(String password) {
		if (password == null || password.isEmpty()) {
			return PasswordStrength.INVALID;
		}

		int meterCount = getMeterCriteriaCounts(password);
		if (meterCount <= 1) {
			return PasswordStrength.WEAK;
		}
		if (meterCount == 2) {
			return PasswordStrength.NORMAL;
		}
		return PasswordStrength.STRONG;
	}

	private int getMeterCriteriaCounts(String password) {
		int meterCount = 0;
		if (password.length() >= 8) {
			meterCount++;
		}
		if (meetsContainingNumberCriteria(password)) {
			meterCount++;
		}
		if (meetsContainingUpperCaseCriteria(password)) {
			meterCount++;
		}
		return meterCount;
	}

	private boolean meetsContainingUpperCaseCriteria(String password) {
		return password.chars().anyMatch(Character::isUpperCase);
	}

	private static boolean meetsContainingNumberCriteria(String password) {
		return password.chars().anyMatch(Character::isDigit);
	}
}
