package cn.zhaoyuening.exception;

public class AccountInfoException  extends Exception{

	public AccountInfoException() {
		super();
	}

	public AccountInfoException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountInfoException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountInfoException(String message) {
		super(message);
	}

	public AccountInfoException(Throwable cause) {
		super(cause);
	}
	
}
