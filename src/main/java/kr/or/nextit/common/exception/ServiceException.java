package kr.or.nextit.common.exception;

public class ServiceException extends RuntimeException{
	 // unchecked Exception, VM이 확인 하냐 안하냐
	//업무적으로 원하는 익셉션을 만들 수 있다. 
	
	//superclass 생성자
	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	//unchecked exception VM이 확인 하냐 안하냐
	
	
	
	
	
	
}
