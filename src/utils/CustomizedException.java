
package utils;

import java.io.Serializable;

public class CustomizedException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

	public CustomizedException(String message) {
		super(message);
	}
}
