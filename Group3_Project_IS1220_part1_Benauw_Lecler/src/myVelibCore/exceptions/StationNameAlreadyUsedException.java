package myVelibCore.exceptions;

public class StationNameAlreadyUsedException extends Exception{
	private String nameAlreadyUsed;

	public StationNameAlreadyUsedException(String nameAlreadyUsed) {
		super();
		this.nameAlreadyUsed = nameAlreadyUsed;
	}
	
	@Override
	public String getMessage() {
		return "The following station name : " + this.nameAlreadyUsed + "is already used. Please pick an other one !";
	}
}
