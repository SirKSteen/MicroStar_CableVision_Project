package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	private static final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	public static boolean isValid(String email) throws CustomizedException {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			throw new CustomizedException("Please enter valid email");
		}
		return matcher.matches();
	}

}
