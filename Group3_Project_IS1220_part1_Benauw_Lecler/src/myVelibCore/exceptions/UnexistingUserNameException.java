package myVelibCore.exceptions;

public class UnexistingUserNameException extends Exception {
	private String unexistingName;
	
	
	
	public UnexistingUserNameException(String unexistingName) {
		super();
		this.unexistingName = unexistingName;
	}



	@Override
	public String getMessage() {
		return "The following name : " + unexistingName + "could not be find in user database.\r\n";
	}

}
